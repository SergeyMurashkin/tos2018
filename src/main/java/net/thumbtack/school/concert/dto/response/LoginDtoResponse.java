package net.thumbtack.school.concert.dto.response;

public class LoginDtoResponse {

    private String token;
    private String error;

    public LoginDtoResponse(){
    }

    public LoginDtoResponse(String token,String error){
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
