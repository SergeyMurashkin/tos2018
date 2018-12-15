package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class GetTrialConcertDtoRequest {

    private String token;

    public GetTrialConcertDtoRequest() {
    }

    public GetTrialConcertDtoRequest(String token) {
        this.token = token;
    }

    public GetTrialConcertDtoRequest createRequest(String jsonRequest) {
        return new Gson().fromJson(jsonRequest, GetTrialConcertDtoRequest.class);
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

