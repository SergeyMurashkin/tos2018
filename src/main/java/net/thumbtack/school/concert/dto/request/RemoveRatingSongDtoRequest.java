package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;


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

    public void validate() throws RequestException {

    }

    public String getToken() {
        return token;
    }

    public Integer getSongId() {
        return songId;
    }


}
