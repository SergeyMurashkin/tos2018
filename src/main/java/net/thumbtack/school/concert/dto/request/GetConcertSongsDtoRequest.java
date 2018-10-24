package net.thumbtack.school.concert.dto.request;


import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;


public class GetConcertSongsDtoRequest {

    private String token;



    public GetConcertSongsDtoRequest(){
    }

    public GetConcertSongsDtoRequest(String token){
        this.token = token;
    }


    public GetConcertSongsDtoRequest createRequest(String  jsonAllConcertSongs) {
        return new Gson().fromJson(jsonAllConcertSongs, GetConcertSongsDtoRequest.class);
    }


    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }else{
            return new Gson().toJson(this, GetConcertSongsDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
