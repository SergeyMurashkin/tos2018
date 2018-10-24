package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

import java.util.HashSet;

public class GetAuthorSongsDtoRequest {

    private String token;
    private HashSet<String> author;

    public GetAuthorSongsDtoRequest(){
    }

    public GetAuthorSongsDtoRequest(String token, HashSet<String> author){
        this.token = token;
        this.author = author;
    }

    public GetAuthorSongsDtoRequest createRequest(String  jsonGetAuthorSongs) {
        return new Gson().fromJson(jsonGetAuthorSongs, GetAuthorSongsDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }else{
            return new Gson().toJson(this, GetAuthorSongsDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HashSet<String> getAuthor() {
        return author;
    }

    public void setAuthor(HashSet<String> author) {
        this.author = author;
    }
}
