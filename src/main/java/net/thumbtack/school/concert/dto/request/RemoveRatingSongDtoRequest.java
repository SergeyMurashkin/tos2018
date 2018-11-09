package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;


public class RemoveRatingSongDtoRequest {

    private String token;
    private Integer songId;

    public RemoveRatingSongDtoRequest() {
    }

    public RemoveRatingSongDtoRequest(String token, Integer songId) {
        this.token = token;
        this.songId = songId;
    }

    public RemoveRatingSongDtoRequest createRequest(String jsonRemoveRating) {
        return new Gson().fromJson(jsonRemoveRating, RemoveRatingSongDtoRequest.class);
    }

    public String validate() {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (!DataBase.getDatabase().isSongSuggested(songId)) {
            return "error: the song not exists";
        }
        if (!DataBase.getDatabase().isUserRatedSong(userLogin, songId)) {
            return "error: you can remove only your rating";
        } else {
            return new Gson().toJson(this, RemoveRatingSongDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public Integer getSongId() {
        return songId;
    }


}
