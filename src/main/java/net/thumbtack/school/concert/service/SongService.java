package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.school.concert.adapter.CommentAdapter;
import net.thumbtack.school.concert.adapter.SongAdapter;
import net.thumbtack.school.concert.daoimpl.SongDaoImpl;
import net.thumbtack.school.concert.dto.request.*;
import net.thumbtack.school.concert.dto.response.*;
import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;
import net.thumbtack.school.concert.requestException.RequestException;

import java.util.List;

public class SongService {

    private SongDaoImpl songDaoImpl = new SongDaoImpl();
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Song.class, new SongAdapter())
            .registerTypeAdapter(Comment.class, new CommentAdapter())
            .create();

    public String suggestSong(String jsonSong) {
        SuggestSongDtoRequest request = new SuggestSongDtoRequest().createSugSongDto(jsonSong);
        SuggestSongDtoResponse response = new SuggestSongDtoResponse();
        try {
            request.validate();
            String token = request.getToken();
            Song song = new Song(request.getTitle(),
                    request.getComposers(),
                    request.getAuthors(),
                    request.getSinger(),
                    request.getDuration());
            String result = songDaoImpl.addSong(token, song);
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, SuggestSongDtoResponse.class);
    }

    public String addRating(String jsonAddRating) {
        AddSongRatingDtoRequest request = new AddSongRatingDtoRequest().createRequest(jsonAddRating);
        AddSongRatingDtoResponse response = new AddSongRatingDtoResponse();
        try {
            request.validate();
            String token = request.getToken();
            Integer songId = request.getSongId();
            Integer rating = request.getRating();
            String result = songDaoImpl.addRating(token, songId, rating);
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, AddSongRatingDtoResponse.class);
    }

    public String removeRating(String jsonRemoveRating) {
        RemoveRatingSongDtoRequest request = new RemoveRatingSongDtoRequest().createRequest(jsonRemoveRating);
        RemoveRatingSongDtoResponse response = new RemoveRatingSongDtoResponse();
        try {
            request.validate();
            String token = request.getToken();
            Integer songId = request.getSongId();
            String result = songDaoImpl.removeRating(token, songId);
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, RemoveRatingSongDtoResponse.class);
    }

    public String addComment(String jsonAddCommentRequest) {
        AddCommentDtoRequest request = new AddCommentDtoRequest().createRequest(jsonAddCommentRequest);
        AddCommentDtoResponse response = new AddCommentDtoResponse();
        try {
            request.validate();
            String token = request.getToken();
            Integer songId = request.getSongId();
            String commentText = request.getText();
            String result = songDaoImpl.addComment(token, songId, commentText);
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, AddCommentDtoResponse.class);
    }

    public String changeComment(String jsonChangeComment) {
        ChangeCommentDtoRequest request = new ChangeCommentDtoRequest().createRequest(jsonChangeComment);
        ChangeCommentDtoResponse response = new ChangeCommentDtoResponse();
        try {
            request.validate();
            String token = request.getToken();
            Integer commentId = request.getCommentId();
            String newCommentText = request.getNewCommentText();
            String result = songDaoImpl.changeComment(token, commentId, newCommentText);
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, ChangeCommentDtoResponse.class);
    }

    public String agreeWithComment(String jsonAgreeWithComment) {
        AgreeWithCommentDtoRequest request = new AgreeWithCommentDtoRequest().createRequest(jsonAgreeWithComment);
        AgreeWithCommentDtoResponse response = new AgreeWithCommentDtoResponse();
        try {
            request.validate();
            String token = request.getToken();
            Integer commentId = request.getCommentId();
            String result = songDaoImpl.agreeWithComment(token, commentId);
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, AgreeWithCommentDtoResponse.class);
    }

    public String getConcertSongs(String jsonGetConcertSongs) {
        GetConcertSongsDtoRequest request = new GetConcertSongsDtoRequest().createRequest(jsonGetConcertSongs);
        GetConcertSongsDtoResponse response = new GetConcertSongsDtoResponse();
        try {
            request.validate();
            List<Song> concertSongs = songDaoImpl.getConcertSongs(request.getToken());
            response.setConcertSongs(concertSongs);
            response.setError(null);
        } catch (RequestException ex) {
            response.setConcertSongs(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, GetConcertSongsDtoResponse.class);
    }

    public String getComposerSongs(String jsonGetComposerSongs) {
        GetComposerSongsDtoRequest request = new GetComposerSongsDtoRequest().createRequest(jsonGetComposerSongs);
        GetComposerSongsDtoResponse response = new GetComposerSongsDtoResponse();
        try {
            request.validate();
            List<Song> composerSongs = songDaoImpl.getComposerSongs(request.getToken(), request.getComposer());
            response.setComposerSongs(composerSongs);
            response.setError(null);
        } catch (RequestException ex) {
            response.setComposerSongs(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, GetComposerSongsDtoResponse.class);
    }

    public String getAuthorSongs(String jsonGetAuthorSongs) {
        GetAuthorSongsDtoRequest request = new GetAuthorSongsDtoRequest().createRequest(jsonGetAuthorSongs);
        GetAuthorSongsDtoResponse response = new GetAuthorSongsDtoResponse();
        try {
            request.validate();
            List<Song> authorSongs = songDaoImpl.getAuthorSongs(request.getToken(), request.getAuthor());
            response.setAuthorSongs(authorSongs);
            response.setError(null);
        } catch (RequestException ex) {
            response.setAuthorSongs(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, GetAuthorSongsDtoResponse.class);
    }

    public String getSingerSongs(String jsonGetSingerSongs) {
        GetSingerSongsDtoRequest request = new GetSingerSongsDtoRequest().createRequest(jsonGetSingerSongs);
        GetSingerSongsDtoResponse response = new GetSingerSongsDtoResponse();
        try {
            request.validate();
            List<Song> singerSongs = songDaoImpl.getSingerSongs(request.getToken(), request.getSinger());
            response.setSingerSongs(singerSongs);
            response.setError(null);
        } catch (RequestException ex) {
            response.setSingerSongs(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, GetSingerSongsDtoResponse.class);
    }

    public String getTrialConcert(String jsonGetTrialConcert) {
        GetTrialConcertDtoRequest request = new GetTrialConcertDtoRequest().createRequest(jsonGetTrialConcert);
        GetTrialConcertDtoResponse response = new GetTrialConcertDtoResponse();
        try {
            request.validate();
            List<TrialConcertSong> trialConcertSongs = songDaoImpl.getTrialConcert(request.getToken());
            response.setTrialConcertSongs(trialConcertSongs);
            response.setError(null);
        } catch (RequestException ex) {
            response.setTrialConcertSongs(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, GetTrialConcertDtoResponse.class);
    }

    public String leaveServer(String jsonLeaveServer) {
        LeaveServerDtoRequest request = new LeaveServerDtoRequest().createRequest(jsonLeaveServer);
        LeaveServerDtoResponse response = new LeaveServerDtoResponse();
        try {
            request.validate();
            String result = songDaoImpl.leaveServer(request.getToken());
            response.setResponse(result);
            response.setError(null);
        } catch (RequestException ex) {
            response.setResponse(null);
            response.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(response, LeaveServerDtoResponse.class);
    }

}
