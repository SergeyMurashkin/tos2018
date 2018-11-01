package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class SuggestSongDtoResponse {

    private String response;
    private String error;

    public SuggestSongDtoResponse() {
    }

    public SuggestSongDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public SuggestSongDtoResponse createResponse(String jsonResponse) {
        return new Gson().fromJson(jsonResponse, SuggestSongDtoResponse.class);
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
