package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class LoginDtoRequest {

    private String login;
    private String password;

    public LoginDtoRequest() {
    }

    public LoginDtoRequest(String login, String password) {
        this.login = login.trim();
        this.password = password.trim();
    }

    public LoginDtoRequest createLoginDto(String jsonLogin) {
        return new Gson().fromJson(jsonLogin, LoginDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (login==null) {
            throw new RequestException(RequestErrorCode.EMPTY_LOGIN_STRING);
        }
        if (password==null) {
            throw new RequestException(RequestErrorCode.EMPTY_PASSWORD_STRING);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }

}
