package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import net.thumbtack.school.concert.daoimpl.SongDaoImpl;
import net.thumbtack.school.concert.dto.request.*;
import net.thumbtack.school.concert.dto.response.*;
import net.thumbtack.school.concert.model.Song;

public class SongService {

    public String suggestSong(String jsonSong) {
        SuggestSongDtoRequest suggestSong = new SuggestSongDtoRequest().createSugSongDto(jsonSong);
        String jsonCheckedRequest = suggestSong.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new SuggestSongDtoResponse(null, jsonCheckedRequest),
                    SuggestSongDtoResponse.class);
        }
        String token = suggestSong.getToken();
        Song song = new Song(suggestSong.getTitle(),
                suggestSong.getComposer(),
                suggestSong.getAuthor(),
                suggestSong.getSinger(),
                suggestSong.getDuration());
        return new SongDaoImpl().addSong(token, song);
    }


    public String addRating(String jsonAddRating) {
        AddRatingSongDtoRequest addRatingSong = new AddRatingSongDtoRequest().createRequest(jsonAddRating);
        String jsonCheckedRequest = addRatingSong.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new AddRatingSongDtoResponse(null, jsonCheckedRequest),
                    AddRatingSongDtoResponse.class);
        }
        String token = addRatingSong.getToken();
        Song song = addRatingSong.getSong();
        Integer rating = addRatingSong.getRating();
        return new SongDaoImpl().addRating(token, song, rating);
    }

    public String removeRating(String jsonRemoveRating) {
        RemoveRatingSongDtoRequest removeRatingSong = new RemoveRatingSongDtoRequest().createRequest(jsonRemoveRating);
        String jsonCheckedRequest = removeRatingSong.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new RemoveRatingSongDtoResponse(null, jsonCheckedRequest),
                    RemoveRatingSongDtoResponse.class);
        }
        String token = removeRatingSong.getToken();
        Song song = removeRatingSong.getSong();
        return new SongDaoImpl().removeRating(token, song);
    }


    public String addComment(String jsonAddCommentRequest) {
        AddCommentDtoRequest addCommentRequest = new AddCommentDtoRequest().createRequest(jsonAddCommentRequest);
        String jsonCheckedRequest = addCommentRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new AddCommentDtoResponse(null, jsonCheckedRequest));
        }
        String token = addCommentRequest.getToken();
        Song song = addCommentRequest.getSong();
        String commentText = addCommentRequest.getCommentText();
        return new SongDaoImpl().addComment(token, song, commentText);
    }

    public String changeComment(String jsonChangeComment) {
        ChangeCommentDtoRequest changeComment = new ChangeCommentDtoRequest().createRequest(jsonChangeComment);
        String jsonCheckedRequest = changeComment.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new ChangeCommentDtoResponse(null, jsonCheckedRequest));
        }
        String token = changeComment.getToken();
        Song song = changeComment.getSong();
        int oldCommentIndex = changeComment.getOldCommentIndex();
        String newCommentText = changeComment.getNewCommentText();
        return new SongDaoImpl().changeComment(token, song, oldCommentIndex, newCommentText);
    }


    public String agreeWithComment(String jsonAgreeWithComment) {
        AgreeWithCommentDtoRequest agreeWithComment = new AgreeWithCommentDtoRequest().createRequest(jsonAgreeWithComment);
        String jsonCheckedRequest = agreeWithComment.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new AgreeWithCommentDtoResponse(null, jsonCheckedRequest));
        }
        String token = agreeWithComment.getToken();
        Song song = agreeWithComment.getSong();
        int commentIndex = agreeWithComment.getCommentIndex();
        return new SongDaoImpl().agreeWithComment(token, song, commentIndex);
    }

    public String getConcertSongs(String jsonGetConcertSongs) {
        GetConcertSongsDtoRequest concertSongs = new GetConcertSongsDtoRequest().createRequest(jsonGetConcertSongs);
        String jsonCheckedRequest = concertSongs.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new GetConcertSongsDtoResponse(null, jsonCheckedRequest));
        }
        return new SongDaoImpl().getConcertSongs();
    }

    public String getComposerSongs(String jsonGetComposerSongs) {
        GetComposerSongsDtoRequest composerSongs = new GetComposerSongsDtoRequest().createRequest(jsonGetComposerSongs);
        String jsonCheckedRequest = composerSongs.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new GetComposerSongsDtoResponse(null, jsonCheckedRequest));
        }
        return new SongDaoImpl().getComposerSongs(composerSongs.getComposer());
    }

    public String getAuthorSongs(String jsonGetAuthorSongs) {
        GetAuthorSongsDtoRequest authorSongs = new GetAuthorSongsDtoRequest().createRequest(jsonGetAuthorSongs);
        String jsonCheckedRequest = authorSongs.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new GetAuthorSongsDtoResponse(null, jsonCheckedRequest));
        }
        return new SongDaoImpl().getAuthorSongs(authorSongs.getAuthor());
    }

    public String getSingerSongs(String jsonGetSingerSongs) {
        GetSingerSongsDtoRequest singerSongs = new GetSingerSongsDtoRequest().createRequest(jsonGetSingerSongs);
        String jsonCheckedRequest = singerSongs.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new GetSingerSongsDtoResponse(null, jsonCheckedRequest));
        }
        return new SongDaoImpl().getSingerSongs(singerSongs.getSinger());
    }

    public String getTrialConcert(String jsonGetTrialConcert) {
        GetTrialConcertDtoRequest trialConcert = new GetTrialConcertDtoRequest().createRequest(jsonGetTrialConcert);
        String jsonCheckedRequest = trialConcert.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new GetSingerSongsDtoResponse(null, jsonCheckedRequest));
        }
        return new SongDaoImpl().getTrialConcert();
    }


    public String leaveServer(String jsonLeaveServer) {
        LeaveServerDtoRequest leaveServerRequest = new LeaveServerDtoRequest().createRequest(jsonLeaveServer);
        String jsonCheckedRequest = leaveServerRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new LeaveServerDtoResponse(null, jsonCheckedRequest),LeaveServerDtoResponse.class);
        }
        String token = leaveServerRequest.getToken();
        return new SongDaoImpl().leaveServer(token);
    }
}
