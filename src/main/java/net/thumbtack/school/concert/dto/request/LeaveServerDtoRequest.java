package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class LeaveServerDtoRequest {

    private String token;

    public LeaveServerDtoRequest() {
    }

    public LeaveServerDtoRequest(String token) {
        this.token = token;
    }

    public LeaveServerDtoRequest createRequest(String jsonRequest) {
        return new Gson().fromJson(jsonRequest, LeaveServerDtoRequest.class);
    }

    public void validate() throws RequestException {
      /*  if (!DataBase.getDatabase().isUserLogged(token)) {
            throw new RequestException(RequestErrorCode.USER_NOT_LOGGED);
        }*/
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
