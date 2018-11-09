package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.model.Song;

public class AddRatingSongDtoRequest {

    private String token;
    private Integer songId;
    private Integer rating;

    public AddRatingSongDtoRequest() {
    }

    public AddRatingSongDtoRequest(String token,
                                   Integer songId,
                                   Integer rating) {
        this.token = token;
        this.songId = songId;
        this.rating = rating;
    }

    public AddRatingSongDtoRequest createRequest(String jsonAddRating) {
        return new Gson().fromJson(jsonAddRating, AddRatingSongDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (!DataBase.getDatabase().isSongSuggested(songId)) {
            return "error: the song not exists";
        }
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        if (DataBase.getDatabase().isUserSuggestedSong(songId, userLogin)) {
            return "error: you are the author suggestion";
        }
        if (rating > 5 || rating < 1) {
            return "error: choose from 1 to 5";
        } else {
            return new Gson().toJson(this, AddRatingSongDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public Integer getSongId() {
        return songId;
    }

    public Integer getRating() {
        return rating;
    }


}
