package net.thumbtack.school.concert.dto.response;

import com.google.gson.Gson;

public class LogoutDtoResponse {

    private String response;
    private String error;

    public LogoutDtoResponse() {
    }

    public LogoutDtoResponse(String response, String error) {
        this.response = response;
        this.error = error;
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

    public LogoutDtoResponse createResponse(String jsonLogoutResponse) {
        return new Gson().fromJson(jsonLogoutResponse, LogoutDtoResponse.class);
    }

}
