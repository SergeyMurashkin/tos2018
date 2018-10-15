package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase implements Serializable {

    private static final long serialVersionUID = -6235928341357577102L;
    private static DataBase database;

    private DataBase(){
    }

    public static DataBase getDatabase() {
        if(database==null){
            database=new DataBase();
        }
        return database;
    }

    private Map<String, User> registeredUsers = new HashMap<>();
    private Map<String, Boolean> logInOutUsers = new HashMap<>();


    public String insert(User user) {
        int before = registeredUsers.size();
        registeredUsers.put(user.getLogin(),user);
        int after = registeredUsers.size();
        RegisterUserDtoResponse userDtoResponse = new RegisterUserDtoResponse();
        if(after>before){
           userDtoResponse.setToken("added");
        }else if(after==before){
            userDtoResponse.setToken("already exist");
        }else{
            userDtoResponse.setToken("WTF");
        }

        logInOutUsers.put(user.getLogin(), false);

        return new Gson().toJson( userDtoResponse, RegisterUserDtoResponse.class) ;
    }

    public String logIn(LoginUserDtoRequest loginUserDto) {
        logInOutUsers.replace(loginUserDto.getLogin(),true);
        return"successful";
    }

    public String logOut(LoginUserDtoRequest logoutUserDto) {
        logInOutUsers.replace(logoutUserDto.getLogin(),false);
        return"successful";
    }



    public Map<String, User> getRegisteredUsers() {
        return registeredUsers;
    }

    public void setRegisteredUsers(Map<String, User> registeredUsers) {
        this.registeredUsers = registeredUsers;
    }

    public Map<String, Boolean> getLogInOutUsers() {
        return logInOutUsers;
    }

    public void setLogInOutUsers(Map<String, Boolean> logInOutUsers) {
        this.logInOutUsers = logInOutUsers;
    }


}
