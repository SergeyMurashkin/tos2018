package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class AddSongRatingDtoResponse {

    private String response;
    private String error;

    public AddSongRatingDtoResponse() {
    }

    public AddSongRatingDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public AddSongRatingDtoResponse createResponse(String jsonAddResponse) {
        return new Gson().fromJson(jsonAddResponse, AddSongRatingDtoResponse.class);
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
