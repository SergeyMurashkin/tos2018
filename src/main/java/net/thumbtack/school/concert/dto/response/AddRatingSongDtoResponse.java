package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class AddRatingSongDtoResponse {

    private String response;
    private String error;

    public AddRatingSongDtoResponse() {
    }

    public AddRatingSongDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
    }

    public AddRatingSongDtoResponse createResponse(String jsonAddResponse) {
        return new Gson().fromJson(jsonAddResponse, AddRatingSongDtoResponse.class);
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
