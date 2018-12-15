package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

import java.util.HashSet;
import java.util.Set;

public class GetAuthorSongsDtoRequest {

    private String token;
    private Set<String> author;

    public GetAuthorSongsDtoRequest() {
    }

    public GetAuthorSongsDtoRequest(String token, Set<String> author) {
        this.token = token;
        this.author = author;
    }

    public GetAuthorSongsDtoRequest createRequest(String jsonGetAuthorSongs) {
        return new Gson().fromJson(jsonGetAuthorSongs, GetAuthorSongsDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (author.size()==0) {
            throw new RequestException(RequestErrorCode.EMPTY_AUTHOR_LIST);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<String> getAuthor() {
        return author;
    }

    public void setAuthor(Set<String> author) {
        this.author = author;
    }

}
