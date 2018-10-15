package net.thumbtack.school.concert.dto.response;

public class RegisterUserDtoResponse {

    private String token;

    public RegisterUserDtoResponse(){
    }

    public RegisterUserDtoResponse(String token){
        this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
