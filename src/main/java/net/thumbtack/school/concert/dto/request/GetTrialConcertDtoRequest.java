package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

public class GetTrialConcertDtoRequest {

    private String token;

    public GetTrialConcertDtoRequest(){
    }

    public GetTrialConcertDtoRequest(String token){
        this.token = token;
    }

    public GetTrialConcertDtoRequest createRequest(String jsonRequest){
        return new Gson().fromJson(jsonRequest, GetTrialConcertDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }else{
            return new Gson().toJson(this, GetTrialConcertDtoRequest.class);
        }
    }
}

