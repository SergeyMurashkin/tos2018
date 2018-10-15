package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.dto.response.LoginUserDtoResponse;

public class LoginUserDtoRequest {

    private String login;
    private String password;

    public LoginUserDtoRequest(){
    }

    public LoginUserDtoRequest(String login, String password){
        this.login = login;
        this.password = password;
    }

    public LoginUserDtoRequest createLoginUserDto(String loginInfo){
        return new Gson().fromJson(loginInfo, LoginUserDtoRequest.class);
    }

    public String validate(){
       if(DataBase.getDatabase().getRegisteredUsers().containsKey(login)
               &&DataBase.getDatabase().getRegisteredUsers().get(login).getPassword().equals(password)){
           return new Gson().toJson(this,LoginUserDtoRequest.class);
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
