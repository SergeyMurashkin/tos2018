package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

public class LogoutDtoRequest {

    private String token;

    public LogoutDtoRequest() {
    }

    public LogoutDtoRequest(String token) {
        this.token = token;
    }

    public LogoutDtoRequest createLogoutDto(String jsonLogoutDto) {
        return new Gson().fromJson(jsonLogoutDto, LogoutDtoRequest.class);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
