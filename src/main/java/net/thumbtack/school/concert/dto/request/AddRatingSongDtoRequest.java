package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.model.Song;

public class AddRatingSongDtoRequest {

    private String token;
    private Song song;
    private Integer rating;

    public AddRatingSongDtoRequest() {
    }

    public AddRatingSongDtoRequest(String token,
                                   Song song,
                                   Integer rating) {
        this.token = token;
        this.song = song;
        this.rating = rating;
    }

    public AddRatingSongDtoRequest createRequest(String jsonAddRating) {
        return new Gson().fromJson(jsonAddRating, AddRatingSongDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (!DataBase.getDatabase().isSongSuggested(song)) {
            return "error: the song not exists";
        }
        if (DataBase.getDatabase().isYourSuggestedSong(song, token)) {
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

    public void setToken(String token) {
        this.token = token;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
