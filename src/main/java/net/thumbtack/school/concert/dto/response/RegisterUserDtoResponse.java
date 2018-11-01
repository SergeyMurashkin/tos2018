package net.thumbtack.school.concert.dto.response;

public class RegisterUserDtoResponse {

    private String token;
    private String error;

    public RegisterUserDtoResponse() {
    }

    public RegisterUserDtoResponse(String token, String error) {
        this.token = token;
        this.error = error;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
