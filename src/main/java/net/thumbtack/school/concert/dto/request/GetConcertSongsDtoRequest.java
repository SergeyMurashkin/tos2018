package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class GetConcertSongsDtoRequest {

    private String token;

    public GetConcertSongsDtoRequest() {
    }

    public GetConcertSongsDtoRequest(String token) {
        this.token = token;
    }

    public GetConcertSongsDtoRequest createRequest(String jsonAllConcertSongs) {
        return new Gson().fromJson(jsonAllConcertSongs, GetConcertSongsDtoRequest.class);
    }

    public void validate() throws RequestException {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
