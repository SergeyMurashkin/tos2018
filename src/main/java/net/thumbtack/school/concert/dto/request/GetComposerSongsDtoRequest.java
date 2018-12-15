package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

import java.util.HashSet;
import java.util.Set;

public class GetComposerSongsDtoRequest {

    private String token;
    private Set<String> composer;

    public GetComposerSongsDtoRequest() {
    }

    public GetComposerSongsDtoRequest(String token, Set<String> composer) {
        this.token = token;
        this.composer = composer;
    }

    public GetComposerSongsDtoRequest createRequest(String jsonGetComposerSongs) {
        return new Gson().fromJson(jsonGetComposerSongs, GetComposerSongsDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (composer.size()==0) {
            throw new RequestException(RequestErrorCode.EMPTY_COMPOSER_LIST);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<String> getComposer() {
        return composer;
    }

    public void setComposer(Set<String> composer) {
        this.composer = composer;
    }

}
