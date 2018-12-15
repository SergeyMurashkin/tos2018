package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class AddSongRatingDtoRequest {

    private String token;
    private Integer songId;
    private Integer rating;

    public AddSongRatingDtoRequest() {
    }

    public AddSongRatingDtoRequest(String token,
                                   Integer songId,
                                   Integer rating) {
        this.token = token;
        this.songId = songId;
        this.rating = rating;
    }

    public AddSongRatingDtoRequest createRequest(String jsonAddRating) {
        return new Gson().fromJson(jsonAddRating, AddSongRatingDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (rating > 5 || rating < 1) {
            throw new RequestException(RequestErrorCode.WRONG_RATING);
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
