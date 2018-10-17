package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

public class RegisterUserDtoRequest {

    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public RegisterUserDtoRequest(){
    }

    public RegisterUserDtoRequest(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

    public RegisterUserDtoRequest createRegUserDto(String jsonString) {
        return new Gson().fromJson(jsonString, RegisterUserDtoRequest.class);
    }

    public String validate() {
        if ( firstName == null || firstName.trim().equals("")
                || lastName == null || lastName.trim().equals("")
                || login == null || login.trim().equals("")
                || password == null || password.trim().equals("")
                || password.trim().length()<6) {
            return "error";
        }else{
            return new Gson().toJson(this, RegisterUserDtoRequest.class);
        }
    }



}
