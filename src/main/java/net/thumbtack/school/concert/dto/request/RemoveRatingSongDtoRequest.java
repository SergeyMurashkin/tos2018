package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.model.Song;

public class RemoveRatingSongDtoRequest {

    private String token;
    private Song song;

    public RemoveRatingSongDtoRequest(){
    }

    public RemoveRatingSongDtoRequest(String token, Song song){
        this.token = token;
        this.song = song;
    }

    public RemoveRatingSongDtoRequest createRequest(String jsonRemoveRating){
       return new Gson().fromJson(jsonRemoveRating, RemoveRatingSongDtoRequest.class);
    }

    public String validate() {
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        if(!DataBase.getDatabase().isUserLogged(token)){
            return "error: please login";
        }
        if(!DataBase.getDatabase().isSongSuggested(song)){
            return "error: the song not exists";
        }
        if(!DataBase.getDatabase().isUserRatedSong(song,authorRequest)){
            return "error: you can remove only your rating";
        }else {
            return new Gson().toJson(this,RemoveRatingSongDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }


}
