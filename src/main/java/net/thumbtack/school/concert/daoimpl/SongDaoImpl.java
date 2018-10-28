package net.thumbtack.school.concert.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.dao.SongDao;
import net.thumbtack.school.concert.dto.response.*;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SongDaoImpl implements SongDao {
    @Override
    public String addSong(String token, Song song) {
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
         SuggestSongDtoResponse suggestSongResponse =  new SuggestSongDtoResponse();
        if (DataBase.getDatabase().isSongSuggested(song)) {
            suggestSongResponse.setError("error: song already added");
        } else {
            DataBase.getDatabase().addSong(song, authorRequest);
            HashMap<String, Integer> firstRating = new HashMap<>();
            firstRating.put(authorRequest, 5);
            DataBase.getDatabase().addFirstRating(song, firstRating);
            suggestSongResponse.setResponse("song added");
        }
        return new Gson().toJson(suggestSongResponse, SuggestSongDtoResponse.class);
    }


    @Override
    public String addRating(String token, Song song, Integer rating) {
        AddRatingSongDtoResponse addRatingSong = new AddRatingSongDtoResponse();
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isUserRatedSong(song, authorRequest)){
            DataBase.getDatabase().changeRating(song,authorRequest,rating);
            addRatingSong.setResponse("rating changed to " + rating);
        }else{
            DataBase.getDatabase().addRating(song, authorRequest, rating);
            addRatingSong.setResponse("rating " + rating + " added");
        }
        return new Gson().toJson(addRatingSong, AddRatingSongDtoResponse.class);
    }

    @Override
    public String removeRating(String token, Song song) {
        RemoveRatingSongDtoResponse removeRatingSong = new RemoveRatingSongDtoResponse();
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        DataBase.getDatabase().removeRating(song, authorRequest);
        if(DataBase.getDatabase().isSongNotRated(song)){
            DataBase.getDatabase().removeSong(song);
            removeRatingSong.setResponse("rating and song removed");
        }else{
            if(DataBase.getDatabase().isUserSuggestedSong(song, token)){
                DataBase.getDatabase().changeAuthorSuggestedSong(song, authorRequest);
                removeRatingSong.setResponse("rating removed and community is new author of suggestion");
            }else {
                removeRatingSong.setResponse("rating removed");
            }
        }
        return new Gson().toJson(removeRatingSong, RemoveRatingSongDtoResponse.class);
    }

    @Override
    public String addComment(String token, Song song, String commentText) {
        AddCommentDtoResponse addComment = new AddCommentDtoResponse();
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        if(DataBase.getDatabase().isSongCommented(song)){
            int maxCommentId = DataBase.getDatabase().getMaxCommentId(song);
            DataBase.getDatabase().addComment(song, new Comment(authorRequest, commentText,maxCommentId+1));
            addComment.setResponse("comment added");
        }else{
            DataBase.getDatabase().addFirstComment(song, new Comment(authorRequest, commentText, 1));
            addComment.setResponse("first comment added");
        }
        return new Gson().toJson(addComment, AddCommentDtoResponse.class);
    }

    @Override
    public String changeComment(String token, Song song, int oldCommentIndex, String newCommentText) {
        ChangeCommentDtoResponse changeComment = new ChangeCommentDtoResponse();
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        DataBase.getDatabase().changeComment(authorRequest, song, oldCommentIndex, newCommentText);
        changeComment.setResponse("comment changed");
        return new Gson().toJson(changeComment, ChangeCommentDtoResponse.class);
    }

    @Override
    public String agreeWithComment(String token, Song song, int commentIndex) {
        AgreeWithCommentDtoResponse agreeWithComment = new AgreeWithCommentDtoResponse();
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        DataBase.getDatabase().agreeWithComment(authorRequest, song, commentIndex);
        if(DataBase.getDatabase().isUserAgreed(song,commentIndex,authorRequest)){
            agreeWithComment.setResponse("change of attitude: you are agreed");
        }else{
            agreeWithComment.setResponse("change of attitude: you are not agreed");
        }

        return new Gson().toJson(agreeWithComment, AgreeWithCommentDtoResponse.class);
    }

    @Override
    public String getConcertSongs() {
        GetConcertSongsDtoResponse concertSongs =
                new GetConcertSongsDtoResponse(DataBase.getDatabase().getConcertSongs(),null);
        return new Gson().toJson(concertSongs, GetConcertSongsDtoResponse.class);
    }

    @Override
    public String getComposerSongs(HashSet<String> composer) {
        GetComposerSongsDtoResponse composerSongs =
                new GetComposerSongsDtoResponse(DataBase.getDatabase().getComposerSongs(composer),null);
        return new Gson().toJson(composerSongs, GetComposerSongsDtoResponse.class);
    }


    public String getAuthorSongs(HashSet<String> author) {
        GetAuthorSongsDtoResponse authorSongs =
                new GetAuthorSongsDtoResponse(DataBase.getDatabase().getAuthorSongs(author),null);
        return new Gson().toJson(authorSongs, GetAuthorSongsDtoResponse.class);
    }

    public String getSingerSongs(String singer) {
        GetSingerSongsDtoResponse singerSongs =
                new GetSingerSongsDtoResponse(DataBase.getDatabase().getSingerSongs(singer),null);
        return new Gson().toJson(singerSongs, GetSingerSongsDtoResponse.class);
    }

    @Override
    public String getTrialConcert() {
        ArrayList<TrialConcertSong> trialConcertSongs = new ArrayList<>();
        int concertDuration = 0;
        ArrayList<Map.Entry<Song,Integer>> sortedSongs = DataBase.getDatabase().getSortedSongs();
        for (Map.Entry<Song, Integer> sortedSong : sortedSongs) {
            if (sortedSong.getKey().getDuration() + concertDuration <= 3600) {
                Song song = sortedSong.getKey();
                String authorSuggestion = DataBase.getDatabase().getAuthorSuggestedSong(song);
                double averageRating = (double) sortedSong.getValue() / DataBase.getDatabase().countSongRating(song);
                ArrayList<Comment> allSongComments = DataBase.getDatabase().getCommentsList(song);
                TrialConcertSong trialConcertSong = new TrialConcertSong(song, authorSuggestion, averageRating, allSongComments);
                trialConcertSongs.add(trialConcertSong);
                concertDuration +=sortedSong.getKey().getDuration() + 10;
            }
            if (concertDuration > 3600) {
                break;
            }
        }
        GetTrialConcertDtoResponse response = new GetTrialConcertDtoResponse(trialConcertSongs,null);
        return new Gson().toJson(response, GetTrialConcertDtoResponse.class);

    }

    @Override
    public String leaveServer(String token) {

        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        ArrayList<Song> userSongs = DataBase.getDatabase().getAllUserSongs(userLogin);
        DataBase.getDatabase().removeUserSongs(userSongs, userLogin);
        DataBase.getDatabase().removeUserRating(userLogin);
        DataBase.getDatabase().removeUserComments(userLogin);
        DataBase.getDatabase().removeUserTokens(userLogin);
        DataBase.getDatabase().removeUserRegistration(userLogin);
        LeaveServerDtoResponse leaveServerResponse = new LeaveServerDtoResponse();
        if(DataBase.getDatabase().isUserLeft(userLogin)){
            leaveServerResponse.setResponse("all mentions deleted");
        }else{
            leaveServerResponse.setError("error: user information not completely deleted");
        }
        return new Gson().toJson(leaveServerResponse, LeaveServerDtoResponse.class);
    }


}

