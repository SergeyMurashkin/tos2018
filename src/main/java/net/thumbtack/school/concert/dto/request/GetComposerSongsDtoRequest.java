package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

import java.util.HashSet;

public class GetComposerSongsDtoRequest {

    private String token;
    private HashSet<String> composer;

    public GetComposerSongsDtoRequest(){
    }

    public GetComposerSongsDtoRequest(String token, HashSet<String> composer){
        this.token = token;
        this.composer = composer;
    }

    public GetComposerSongsDtoRequest createRequest(String  jsonGetComposerSongs) {
        return new Gson().fromJson(jsonGetComposerSongs, GetComposerSongsDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }else{
            return new Gson().toJson(this, GetComposerSongsDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HashSet<String> getComposer() {
        return composer;
    }

    public void setComposer(HashSet<String> composer) {
        this.composer = composer;
    }
}
