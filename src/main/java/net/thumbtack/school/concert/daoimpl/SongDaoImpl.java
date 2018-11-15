package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.dao.SongDao;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Rating;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SongDaoImpl implements SongDao {

    @Override
    public String addSong(String token, Song song) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isSongSuggested(song)) {
            return "error: song already added";
        } else {
            Integer songId = DataBase.getDatabase().getFreeMaxSongId();
            song.setSongId(songId);
            song.setUserLogin(userLogin);
            DataBase.getDatabase().addSong(song);
            DataBase.getDatabase().addSongToUser(userLogin, songId);

            Integer maxRaring = 5;
            Rating songRating = new Rating(userLogin, songId, maxRaring);
            Integer ratingId = DataBase.getDatabase().getFreeMaxRatingId();
            songRating.setRatingId(ratingId);
            DataBase.getDatabase().addRating(songRating);
            DataBase.getDatabase().addRatingToUser(userLogin, ratingId);
            DataBase.getDatabase().addRatingToSong(songId, ratingId);

            return "song added";
        }
    }


    @Override
    public String addRating(String token, Integer songId, Integer rating) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isUserRatedSong(userLogin, songId)) {
            Integer ratingId = DataBase.getDatabase().getRatingId(userLogin, songId);
            DataBase.getDatabase().changeRating(ratingId, rating);
            return "rating changed to " + rating;
        } else {
            Rating songRating = new Rating(userLogin, songId, rating);
            Integer newRatingId = DataBase.getDatabase().getFreeMaxRatingId();
            songRating.setRatingId(newRatingId);
            DataBase.getDatabase().addRating(songRating);
            DataBase.getDatabase().addRatingToUser(userLogin, newRatingId);
            DataBase.getDatabase().addRatingToSong(songId, newRatingId);
            return "rating " + rating + " added";
        }
    }

    @Override
    public String removeRating(String token, Integer songId) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        Integer ratingId = DataBase.getDatabase().getRatingId(userLogin, songId);
        DataBase.getDatabase().removeRating(ratingId);
        DataBase.getDatabase().removeRatingFromUser(userLogin, songId);
        DataBase.getDatabase().removeRatingFromSong(songId, ratingId);
        if (DataBase.getDatabase().isSongNotRated(songId)) {
            if (DataBase.getDatabase().isUserSuggestedSong(songId, token)) {
                DataBase.getDatabase().removeSong(songId);
                DataBase.getDatabase().removeSongFromUser(userLogin, songId);
                return "rating and song removed";
            } else {
                return "rating removed";
            }

        } else {
            if (DataBase.getDatabase().isUserSuggestedSong(songId, userLogin)) {
                DataBase.getDatabase().changeSuggestedSongUser(songId, userLogin, DataBase.COMMUNITY_LOGIN);
                return "rating removed and community is new author of suggestion";
            } else {
                return "rating removed";
            }
        }
    }

    @Override
    public String addComment(String token, Integer songId, String commentText) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        Comment comment = new Comment(userLogin, songId, commentText);
        Integer commentId = DataBase.getDatabase().getFreeMaxCommentId();
        comment.setCommentId(commentId);
        DataBase.getDatabase().addComment(comment);
        DataBase.getDatabase().addCommentToUser(userLogin, commentId);
        DataBase.getDatabase().addCommentToSong(songId, commentId);
        return "comment added";
    }

    @Override
    public String changeComment(String token, Integer commentId, Integer songId, String newCommentText) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (!DataBase.getDatabase().isCommentWithJoins(commentId)) {
            DataBase.getDatabase().changeCommentText(commentId, newCommentText);
            return "comment changed";
        } else {
            DataBase.getDatabase().changeCommentAuthor(commentId, userLogin, DataBase.COMMUNITY_LOGIN);
            Comment comment = new Comment(userLogin, songId, newCommentText);
            Integer newCommentId = DataBase.getDatabase().getFreeMaxCommentId();
            comment.setCommentId(newCommentId);
            DataBase.getDatabase().addComment(comment);
            DataBase.getDatabase().addCommentToUser(userLogin, commentId);
            DataBase.getDatabase().addCommentToSong(songId, commentId);
            return "comment changed and added new comment";
        }
    }

    @Override
    public String agreeWithComment(String token, Integer commentId) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (!DataBase.getDatabase().isUserAgreeWithComment(userLogin, commentId)) {
            DataBase.getDatabase().agreeWithComment(userLogin, commentId);
            return "change of attitude: you are agreed";
        } else {
            DataBase.getDatabase().disagreeWithComment(userLogin, commentId);
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
                String authorSuggestion = DataBase.getDatabase().getAuthorSuggestedSong(song.getSongId());
                double averageRating = (double) sortedSong.getValue() / DataBase.getDatabase().countSongRatings(song);
                List<Comment> allSongComments = DataBase.getDatabase().getSongComments(song);
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
        DataBase.getDatabase().removeUserSongs(userLogin);
        DataBase.getDatabase().removeUserRatings(userLogin);
        DataBase.getDatabase().removeUserAgrees(userLogin);
        DataBase.getDatabase().removeUserComments(userLogin);
        DataBase.getDatabase().removeUserTokens(userLogin);
        DataBase.getDatabase().removeUserRegistration(userLogin);
        if (DataBase.getDatabase().isUserLeft(userLogin)) {
            return "all mentions deleted";
        } else {
            return "error: user information not completely deleted";
        }
    }

}

