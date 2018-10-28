package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

public class LeaveServerDtoRequest {

    private String token;

    public LeaveServerDtoRequest() {
    }

    public LeaveServerDtoRequest(String token) {
        this.token = token;
    }

    public LeaveServerDtoRequest createRequest(String  jsonRequest){
        return new Gson().fromJson(jsonRequest, LeaveServerDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }else{
            return "valid request";
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
