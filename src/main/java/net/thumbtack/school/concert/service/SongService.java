package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import net.thumbtack.school.concert.daoimpl.SongDaoImpl;
import net.thumbtack.school.concert.dto.request.*;
import net.thumbtack.school.concert.dto.response.*;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.List;

public class SongService {

    private SongDaoImpl songDaoImpl = new SongDaoImpl();
    private Gson gson = new Gson();
    private SuggestSongDtoRequest suggestSongRequest = new SuggestSongDtoRequest();
    private SuggestSongDtoResponse suggestSongResponse = new SuggestSongDtoResponse();
    private AddRatingSongDtoRequest addRatingRequest = new AddRatingSongDtoRequest();
    private AddRatingSongDtoResponse addRatingResponse = new AddRatingSongDtoResponse();
    private RemoveRatingSongDtoRequest removeRatingRequest = new RemoveRatingSongDtoRequest();
    private RemoveRatingSongDtoResponse removeRatingResponse = new RemoveRatingSongDtoResponse();
    private AddCommentDtoRequest addCommentRequest = new AddCommentDtoRequest();
    private AddCommentDtoResponse addCommentResponse = new AddCommentDtoResponse();
    private ChangeCommentDtoRequest changeCommentRequest = new ChangeCommentDtoRequest();
    private ChangeCommentDtoResponse changeCommentResponse = new ChangeCommentDtoResponse();
    private AgreeWithCommentDtoRequest agreeWithCommentRequest = new AgreeWithCommentDtoRequest();
    private AgreeWithCommentDtoResponse agreeWithCommentResponse = new AgreeWithCommentDtoResponse();
    private GetConcertSongsDtoRequest getConcertSongsRequest = new GetConcertSongsDtoRequest();
    private GetConcertSongsDtoResponse getConcertSongsResponse = new GetConcertSongsDtoResponse();
    private GetComposerSongsDtoRequest getComposerSongsRequest = new GetComposerSongsDtoRequest();
    private GetComposerSongsDtoResponse getComposerSongsResponse = new GetComposerSongsDtoResponse();
    private GetAuthorSongsDtoRequest getAuthorSongsRequest = new GetAuthorSongsDtoRequest();
    private GetAuthorSongsDtoResponse getAuthorSongsResponse = new GetAuthorSongsDtoResponse();
    private GetSingerSongsDtoRequest getSingerSongsRequest = new GetSingerSongsDtoRequest();
    private GetSingerSongsDtoResponse getSingerSongsResponse = new GetSingerSongsDtoResponse();
    private GetTrialConcertDtoRequest getTrialConcertRequest = new GetTrialConcertDtoRequest();
    private GetTrialConcertDtoResponse getTrialConcertResponse = new GetTrialConcertDtoResponse();
    private LeaveServerDtoRequest leaveServerRequest = new LeaveServerDtoRequest();
    private LeaveServerDtoResponse leaveServerResponse = new LeaveServerDtoResponse();

    public String suggestSong(String jsonSong) {
        suggestSongRequest = suggestSongRequest.createSugSongDto(jsonSong);
        String jsonCheckedRequest = suggestSongRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            suggestSongResponse.setResponse(null);
            suggestSongResponse.setError(jsonCheckedRequest);
        } else {
            String token = suggestSongRequest.getToken();
            Song song = new Song(suggestSongRequest.getTitle(),
                    suggestSongRequest.getComposer(),
                    suggestSongRequest.getAuthor(),
                    suggestSongRequest.getSinger(),
                    suggestSongRequest.getDuration());
            String response = songDaoImpl.addSong(token, song);
            if (response.contains("error:")) {
                suggestSongResponse.setResponse(null);
                suggestSongResponse.setError(response);
            } else {
                suggestSongResponse.setResponse(response);
                suggestSongResponse.setError(null);
            }
        }
        return gson.toJson(suggestSongResponse, SuggestSongDtoResponse.class);
    }

    public String addRating(String jsonAddRating) {
        addRatingRequest = addRatingRequest.createRequest(jsonAddRating);
        String jsonCheckedRequest = addRatingRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            addRatingResponse.setResponse(null);
            addRatingResponse.setError(jsonCheckedRequest);
        } else {
            String token = addRatingRequest.getToken();
            Song song = addRatingRequest.getSong();
            Integer rating = addRatingRequest.getRating();
            String response = songDaoImpl.addRating(token, song, rating);
            addRatingResponse.setResponse(response);
            addRatingResponse.setError(null);
        }
        return gson.toJson(addRatingResponse, AddRatingSongDtoResponse.class);
    }

    public String removeRating(String jsonRemoveRating) {
        removeRatingRequest = removeRatingRequest.createRequest(jsonRemoveRating);
        String jsonCheckedRequest = removeRatingRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            removeRatingResponse.setResponse(null);
            removeRatingResponse.setError(jsonCheckedRequest);
        } else {
            String token = removeRatingRequest.getToken();
            Song song = removeRatingRequest.getSong();
            String response = songDaoImpl.removeRating(token, song);
            removeRatingResponse.setResponse(response);
            removeRatingResponse.setError(null);
        }
        return gson.toJson(removeRatingResponse, RemoveRatingSongDtoResponse.class);
    }

    public String addComment(String jsonAddCommentRequest) {
        addCommentRequest = addCommentRequest.createRequest(jsonAddCommentRequest);
        String jsonCheckedRequest = addCommentRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            addRatingResponse.setResponse(null);
            addCommentResponse.setError(jsonCheckedRequest);
        } else {
            String token = addCommentRequest.getToken();
            Song song = addCommentRequest.getSong();
            String commentText = addCommentRequest.getCommentText();
            String response = songDaoImpl.addComment(token, song, commentText);
            addCommentResponse.setResponse(response);
            addCommentResponse.setError(null);
        }
        return gson.toJson(addCommentResponse, AddCommentDtoResponse.class);
    }

    public String changeComment(String jsonChangeComment) {
        changeCommentRequest = changeCommentRequest.createRequest(jsonChangeComment);
        String jsonCheckedRequest = changeCommentRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            changeCommentResponse.setResponse(null);
            changeCommentResponse.setError(jsonCheckedRequest);
        } else {
            String token = changeCommentRequest.getToken();
            Song song = changeCommentRequest.getSong();
            int oldCommentIndex = changeCommentRequest.getOldCommentIndex();
            String newCommentText = changeCommentRequest.getNewCommentText();
            String response = songDaoImpl.changeComment(token, song, oldCommentIndex, newCommentText);
            changeCommentResponse.setResponse(response);
            changeCommentResponse.setError(null);
        }
        return gson.toJson(changeCommentResponse, ChangeCommentDtoResponse.class);
    }

    public String agreeWithComment(String jsonAgreeWithComment) {
        agreeWithCommentRequest = agreeWithCommentRequest.createRequest(jsonAgreeWithComment);
        String jsonCheckedRequest = agreeWithCommentRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            agreeWithCommentResponse.setResponse(null);
            agreeWithCommentResponse.setError(jsonCheckedRequest);
        } else {
            String token = agreeWithCommentRequest.getToken();
            Song song = agreeWithCommentRequest.getSong();
            int commentIndex = agreeWithCommentRequest.getCommentIndex();
            String response = songDaoImpl.agreeWithComment(token, song, commentIndex);
            agreeWithCommentResponse.setResponse(response);
            agreeWithCommentResponse.setError(null);
        }
        return gson.toJson(agreeWithCommentResponse, AgreeWithCommentDtoResponse.class);
    }

    public String getConcertSongs(String jsonGetConcertSongs) {
        getConcertSongsRequest = getConcertSongsRequest.createRequest(jsonGetConcertSongs);
        String jsonCheckedRequest = getConcertSongsRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            getConcertSongsResponse.setError(jsonCheckedRequest);
            getConcertSongsResponse.setConcertSongs(null);
        } else {
            List<Song> concertSongs = songDaoImpl.getConcertSongs();
            getConcertSongsResponse.setConcertSongs(concertSongs);
            getConcertSongsResponse.setError(null);
        }
        return gson.toJson(getConcertSongsResponse, GetConcertSongsDtoResponse.class);
    }

    public String getComposerSongs(String jsonGetComposerSongs) {
        getComposerSongsRequest = getComposerSongsRequest.createRequest(jsonGetComposerSongs);
        String jsonCheckedRequest = getComposerSongsRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            getComposerSongsResponse.setComposerSongs(null);
            getComposerSongsResponse.setError(jsonCheckedRequest);
        }
        List<Song> composerSongs = songDaoImpl.getComposerSongs(getComposerSongsRequest.getComposer());
        getComposerSongsResponse.setComposerSongs(composerSongs);
        getComposerSongsResponse.setError(null);
        return gson.toJson(getComposerSongsResponse, GetComposerSongsDtoResponse.class);
    }

    public String getAuthorSongs(String jsonGetAuthorSongs) {
        getAuthorSongsRequest = getAuthorSongsRequest.createRequest(jsonGetAuthorSongs);
        String jsonCheckedRequest = getAuthorSongsRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            getAuthorSongsResponse.setAuthorSongs(null);
            getAuthorSongsResponse.setError(jsonCheckedRequest);
        }
        List<Song> authorSongs = songDaoImpl.getAuthorSongs(getAuthorSongsRequest.getAuthor());
        getAuthorSongsResponse.setAuthorSongs(authorSongs);
        getAuthorSongsResponse.setError(null);
        return gson.toJson(getAuthorSongsResponse, GetAuthorSongsDtoResponse.class);
    }

    public String getSingerSongs(String jsonGetSingerSongs) {
        getSingerSongsRequest = getSingerSongsRequest.createRequest(jsonGetSingerSongs);
        String jsonCheckedRequest = getSingerSongsRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            getSingerSongsResponse.setSingerSongs(null);
            getSingerSongsResponse.setError(jsonCheckedRequest);
        }
        List<Song> singerSongs = songDaoImpl.getSingerSongs(getSingerSongsRequest.getSinger());
        getSingerSongsResponse.setSingerSongs(singerSongs);
        getSingerSongsResponse.setError(null);
        return gson.toJson(getSingerSongsResponse, GetSingerSongsDtoResponse.class);
    }

    public String getTrialConcert(String jsonGetTrialConcert) {
        getTrialConcertRequest = getTrialConcertRequest.createRequest(jsonGetTrialConcert);
        String jsonCheckedRequest = getTrialConcertRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            getTrialConcertResponse.setTrialConcertSongs(null);
            getTrialConcertResponse.setError(jsonCheckedRequest);
        }
        List<TrialConcertSong> trialConcertSongs = songDaoImpl.getTrialConcert();
        getTrialConcertResponse.setTrialConcertSongs(trialConcertSongs);
        getTrialConcertResponse.setError(null);
        return gson.toJson(getTrialConcertResponse, GetTrialConcertDtoResponse.class);
    }

    public String leaveServer(String jsonLeaveServer) {
        leaveServerRequest = leaveServerRequest.createRequest(jsonLeaveServer);
        String jsonCheckedRequest = leaveServerRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            leaveServerResponse.setResponse(null);
            leaveServerResponse.setError(jsonCheckedRequest);
        } else {
            String response = songDaoImpl.leaveServer(leaveServerRequest.getToken());
            if (response.contains("error:")) {
                leaveServerResponse.setResponse(null);
                leaveServerResponse.setError(response);
            } else {
                leaveServerResponse.setResponse(response);
                leaveServerResponse.setError(null);
            }
        }
        return gson.toJson(leaveServerResponse, LeaveServerDtoResponse.class);
    }

}
