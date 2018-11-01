package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.dao.SongDao;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.*;

public class SongDaoImpl implements SongDao {

    @Override
    public String addSong(String token, Song song) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isSongSuggested(song)) {
            return "error: song already added";
        } else {
            DataBase.getDatabase().addSong(song, userLogin);
            HashMap<String, Integer> firstRating = new HashMap<>();
            firstRating.put(userLogin, 5);
            DataBase.getDatabase().addFirstRating(song, firstRating);
            return "song added";
        }
    }

    @Override
    public String addRating(String token, Song song, Integer rating) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isUserRatedSong(song, userLogin)) {
            DataBase.getDatabase().changeRating(song, userLogin, rating);
            return "rating changed to " + rating;
        } else {
            DataBase.getDatabase().addRating(song, userLogin, rating);
            return "rating " + rating + " added";
        }
    }

    @Override
    public String removeRating(String token, Song song) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        DataBase.getDatabase().removeRating(song, userLogin);
        if (DataBase.getDatabase().isSongNotRated(song)) {
            DataBase.getDatabase().removeSong(song);
            return "rating and song removed";
        } else {
            if (DataBase.getDatabase().isUserSuggestedSong(song, token)) {
                DataBase.getDatabase().changeAuthorSuggestedSong(song, userLogin, DataBase.COMMUNITY_LOGIN);
                return "rating removed and community is new author of suggestion";
            } else {
                return "rating removed";
            }
        }
    }

    @Override
    public String addComment(String token, Song song, String commentText) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isSongCommented(song)) {
            int maxCommentId = DataBase.getDatabase().getMaxCommentId(song);
            DataBase.getDatabase().addComment(song, new Comment(userLogin, commentText, maxCommentId + 1));
            return "comment added";
        } else {
            DataBase.getDatabase().addFirstComment(song, new Comment(userLogin, commentText, 1));
            return "first comment added";
        }
    }

    @Override
    public String changeComment(String token, Song song, int oldCommentIndex, String newCommentText) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        DataBase.getDatabase().changeComment(userLogin, song, oldCommentIndex, newCommentText);
        return "comment changed";
    }

    @Override
    public String agreeWithComment(String token, Song song, int commentIndex) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        DataBase.getDatabase().agreeWithComment(userLogin, song, commentIndex);
        if (DataBase.getDatabase().isUserAgreed(song, commentIndex, userLogin)) {
            return "change of attitude: you are agreed";
        } else {
            return "change of attitude: you are not agreed";
        }
    }

    @Override
    public List<Song> getConcertSongs() {
        return DataBase.getDatabase().getConcertSongs();
    }

    @Override
    public List<Song> getComposerSongs(Set<String> composer) {
        return DataBase.getDatabase().getComposerSongs(composer);
    }

    public List<Song> getAuthorSongs(Set<String> author) {
        return DataBase.getDatabase().getAuthorSongs(author);

    }

    public List<Song> getSingerSongs(String singer) {
        return DataBase.getDatabase().getSingerSongs(singer);
    }

    @Override
    public List<TrialConcertSong> getTrialConcert() {
        List<TrialConcertSong> trialConcertSongs = new ArrayList<>();
        int concertDuration = 0;
        List<Map.Entry<Song, Integer>> sortedSongs = DataBase.getDatabase().sortSongsBySumRating();
        for (Map.Entry<Song, Integer> sortedSong : sortedSongs) {
            if (sortedSong.getKey().getDuration() + concertDuration <= 3600) {
                Song song = sortedSong.getKey();
                String authorSuggestion = DataBase.getDatabase().getAuthorSuggestedSong(song);
                double averageRating = (double) sortedSong.getValue() / DataBase.getDatabase().countSongRating(song);
                List<Comment> allSongComments = DataBase.getDatabase().getCommentsList(song);
                TrialConcertSong trialConcertSong = new TrialConcertSong(song, authorSuggestion, averageRating, allSongComments);
                trialConcertSongs.add(trialConcertSong);
                concertDuration += sortedSong.getKey().getDuration() + 10;
            }
            if (concertDuration > 3600) {
                break;
            }
        }
        return trialConcertSongs;
    }

    @Override
    public String leaveServer(String token) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        List<Song> userSongs = DataBase.getDatabase().getAllUserSongs(userLogin);
        DataBase.getDatabase().removeUserSongs(userSongs, userLogin);
        DataBase.getDatabase().removeUserRating(userLogin);
        DataBase.getDatabase().removeUserCommentsAndAgrees(userLogin);
        DataBase.getDatabase().removeUserTokens(userLogin);
        DataBase.getDatabase().removeUserRegistration(userLogin);
        if (DataBase.getDatabase().isUserLeft(userLogin)) {
            return "all mentions deleted";
        } else {
            return "error: user information not completely deleted";
        }
    }

}

