package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class RemoveRatingSongDtoResponse {

    private String response;
    private String error;

    public RemoveRatingSongDtoResponse() {
    }

    public RemoveRatingSongDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public RemoveRatingSongDtoResponse createResponse(String jsonResponse) {
        return new Gson().fromJson(jsonResponse, RemoveRatingSongDtoResponse.class);
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
