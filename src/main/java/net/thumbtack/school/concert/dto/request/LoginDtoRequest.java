package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

public class LoginDtoRequest {

    private String login;
    private String password;

    public LoginDtoRequest(){
    }

    public LoginDtoRequest(String login, String password){
        this.login = login;
        this.password = password;
    }

    public LoginDtoRequest createLoginDto(String jsonLogin){
        return new Gson().fromJson(jsonLogin, LoginDtoRequest.class);
    }

    public String validate(){
       if(DataBase.getDatabase().isUserRegistered(login)
               &&DataBase.getDatabase().isPasswordRight(login, password)){
           return new Gson().toJson(this, LoginDtoRequest.class);
       }else{
           return "error";
       }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
