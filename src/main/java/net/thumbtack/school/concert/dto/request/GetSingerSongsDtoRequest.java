package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

public class GetSingerSongsDtoRequest {

    private String token;
    private String singer;

    public GetSingerSongsDtoRequest(){
    }

    public GetSingerSongsDtoRequest(String token, String singer){
        this.token = token;
        this.singer = singer;
    }

    public GetSingerSongsDtoRequest createRequest(String  jsonGetSingerSongs) {
        return new Gson().fromJson(jsonGetSingerSongs, GetSingerSongsDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }else{
            return new Gson().toJson(this, GetSingerSongsDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
