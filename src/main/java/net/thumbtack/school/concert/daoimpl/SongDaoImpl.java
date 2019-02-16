package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.dao.SongDao;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Rating;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;
import net.thumbtack.school.database.mybatis.daoimpl.GroupDaoImpl;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class SongDaoImpl extends DaoImplBase implements SongDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDaoImpl.class);

    @Override
    public String addSong(String token, Song song) throws RequestException {
        LOGGER.debug("DAO add Song {}.", song);

        try (SqlSession sqlSession = getSession()) {
            try {
                Integer userId = getUserMapper(sqlSession).getUserIdByToken(token);
                if(userId==null){
                    throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
                }
                getSongMapper(sqlSession).addSong(song, userId);
                for (String composer : song.getComposers()) {
                    getSongMapper(sqlSession).addComposerToSong(song.getId(), composer);
                }
                for (String author : song.getAuthors()) {
                    getSongMapper(sqlSession).addAuthorToSong(song.getId(), author);
                }
                Integer maxRating = 5;
                getRatingMapper(sqlSession).addRating(song.getId(), userId, maxRating);
            } catch (PersistenceException ex3) {
                LOGGER.info("Can't add Song duplicate {}.", song, ex3);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.SONG_DUPLICATE);
            } catch (RuntimeException ex1) {
                LOGGER.info("Can't add Song {}.", song, ex1);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_ADDING);
            }
            sqlSession.commit();
        }
        return "Song № " + song.getId() + " added. Song rated by 5, automatically.";
    }

    @Override
    public String addRating(String token, Integer songId, Integer rating) throws RequestException {
        LOGGER.debug("DAO add Rating {}.", rating);
        try (SqlSession sqlSession = getSession()) {
            try {
                Integer userId = getUserMapper(sqlSession).getUserIdByToken(token);
                if(userId==null){
                    throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
                }
                Integer authorId = getSongMapper(sqlSession).getUserIdBySongId(songId);
                if (userId.equals(authorId)) {
                    throw new RequestException(RequestErrorCode.RATING_BAN);
                } else {
                    try {
                        getRatingMapper(sqlSession).addRating(songId, userId, rating);
                    } catch (PersistenceException ex3) {
                        LOGGER.info("DAO change Rating.", ex3);
                        sqlSession.rollback();
                        getRatingMapper(sqlSession).changeRating(songId, userId, rating);
                    }
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't add Rating.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_RATING_ADDING);
            }
            sqlSession.commit();
        }
        return "Rating " + rating + " added.";
    }

    @Override
    public String removeRating(String token, Integer songId) throws RequestException {
        LOGGER.debug("DAO delete Rating of Song {}.", songId);
        String response = "Rating removed.";
        try (SqlSession sqlSession = getSession()) {
            try {
                Integer userId = getUserMapper(sqlSession).getUserIdByToken(token);
                Integer authorId = getSongMapper(sqlSession).getUserIdBySongId(songId);
                getRatingMapper(sqlSession).deleteRating(userId, songId);
                Set<Integer> songRatingsId = getRatingMapper(sqlSession).getSongRatingsId(songId);
                if (songRatingsId.size() == 0) {
                    getSongMapper(sqlSession).deleteSong(songId);
                    response = "Rating and song removed.";
                } else {
                    if (userId.equals(authorId)) {
                        int userCommunityId = 1;
                        getSongMapper(sqlSession).changeSongUserId(songId, userCommunityId);
                        response = "Rating removed. Community is a new author of the song.";
                    }
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Rating.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_RATING_REMOVING);
            }
            sqlSession.commit();
        }
        return response;
    }


    @Override
    public String addComment(String token, Integer songId, String text) throws RequestException {
        LOGGER.debug("DAO add Comment: {}.", text);
        Integer commentId;
        try (SqlSession sqlSession = getSession()) {
            try {
                Integer userId = getUserMapper(sqlSession).getUserIdByToken(token);
                Comment comment = new Comment(songId, userId, text);
                getCommentMapper(sqlSession).addComment(comment);
                commentId = comment.getId();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't add Comment.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_COMMENT_ADDING);
            }
            sqlSession.commit();
        }
        return "Comment № " + commentId + " added.";
    }

    @Override
    public String changeComment(String token, Integer commentId, String newCommentText) throws RequestException {
        LOGGER.debug("DAO change Comment.");
        try (SqlSession sqlSession = getSession()) {
            try {
                Integer userId = getUserMapper(sqlSession).getUserIdByToken(token);
                getCommentMapper(sqlSession).changeComment(commentId, userId, newCommentText);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't change Comment.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_COMMENT_CHANGING);
            }
            sqlSession.commit();
        }
        return "Comment № " + commentId + " changed.";
    }

    @Override
    public String agreeWithComment(String token, Integer commentId) throws RequestException {
        LOGGER.debug("DAO agree with Comment, {}.", commentId);
        String response;
        try (SqlSession sqlSession = getSession()) {
            try {
                Integer userId = getUserMapper(sqlSession).getUserIdByToken(token);
                if (getCommentMapper(sqlSession).isAgreementExists(commentId, userId)) {
                    getCommentMapper(sqlSession).disagreeWithComment(commentId, userId);
                    response = "Attitude changing: you are not agreed.";
                } else {
                    getCommentMapper(sqlSession).agreeWithComment(commentId, userId);
                    response = "Attitude changing: you are agreed.";
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't agree with Comment.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_COMMENT_CHANGING);
            }
            sqlSession.commit();
        }
        return response;
    }

    @Override
    public List<Song> getConcertSongs(String token) throws RequestException {
        LOGGER.debug("DAO return all Songs.");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("User not logged.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
            }
            try {
                return getSongMapper(sqlSession).getAllSongs();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return all Songs.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }
    }

    @Override
    public Song getSongById(Integer songId) throws RequestException {
        LOGGER.debug("DAO return Song by id.");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getSongMapper(sqlSession).getSongById(songId);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return Songs by id.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }
    }

    @Override
    public List<Song> getComposerSongs(String token , Set<String> composers) throws RequestException {
        LOGGER.debug("DAO return all composer Songs.");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("User not logged.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
            }
            try {
               return getSongMapper(sqlSession).getComposersSongs(composers);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return all Songs.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }
    }

    public List<Song> getAuthorSongs(String token, Set<String> authors) throws RequestException {
        LOGGER.debug("DAO return all author Songs.");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("User not logged.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
            }
            try {
                return getSongMapper(sqlSession).getAuthorsSongs(authors);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return all Songs.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }
    }

    public List<Song> getSingerSongs(String  token, String singer) throws RequestException {
        LOGGER.debug("DAO return all singer Songs.");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("User not logged.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
            }
            try {
                return getSongMapper(sqlSession).getSingerSongs(singer);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return all Songs.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }
    }

    @Override
    public List<TrialConcertSong> getTrialConcert(String token) throws RequestException {
        LOGGER.debug("DAO return all trial concert Songs.");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("User not logged.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
            }
            try {
                List<TrialConcertSong> trialConcertSongs = new ArrayList<>();
                Map<Integer, Song> song_sumRating = new TreeMap<>(Collections.reverseOrder());
                List<Song> allSongs = getSongMapper(sqlSession).getAllSongs();
                for(Song song : allSongs) {
                    int sumRating = 0;
                    for(Rating rating : song.getRatings()){
                        sumRating += rating.getRating();
                    }
                    song_sumRating.put(sumRating, song);
                }
                Set<Integer> songSumRatings = song_sumRating.keySet();
                int concertTimeLimit = 0;
                for(Integer sumRating : songSumRatings){
                    Song song = song_sumRating.get(sumRating);
                    if(song.getDuration()+concertTimeLimit<=3600){
                        TrialConcertSong trialConcertSong = new TrialConcertSong();
                        trialConcertSong.setSong(song);
                        trialConcertSong.setUser(song.getUserId());
                        trialConcertSong.setAllSongComments(song.getComments());
                        double averageRating = (double) sumRating/song.getRatings().size();
                        trialConcertSong.setAverageRating(averageRating);
                        trialConcertSongs.add(trialConcertSong);
                        concertTimeLimit+=song.getDuration();
                    }
                }
                return trialConcertSongs;
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return all Songs.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }
    }

    @Override
    public String leaveServer(String token) throws RequestException {
        LOGGER.debug("DAO remove User");
        Integer userId;
        try (SqlSession sqlSession = getSession()) {
            try {
                userId = getUserMapper(sqlSession).getUserIdByToken(token);
            } catch (RuntimeException ex) {
                LOGGER.info("User not logged.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
            }
            try {
                getUserMapper(sqlSession).deleteUserById(userId);
                return "All user mentions deleted.";
            } catch (RuntimeException ex) {
                LOGGER.info("Can't return all Songs.", ex);
                sqlSession.rollback();
                throw new RequestException(RequestErrorCode.FAILED_SONG_EXISTING);
            }
        }

    }
}


