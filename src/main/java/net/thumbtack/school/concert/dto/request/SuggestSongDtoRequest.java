package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

import java.util.List;

public class SuggestSongDtoRequest {

    private String token;
    private String title;
    private List<String> composers;
    private List<String> authors;
    private String singer;
    private int duration;

    public SuggestSongDtoRequest() {
    }

    public SuggestSongDtoRequest(String token,
                                 String title,
                                 List<String> composers,
                                 List<String> authors,
                                 String singer,
                                 int duration) {
        this.token = token;
        this.title = title;
        this.composers = composers;
        this.authors = authors;
        this.singer = singer;
        this.duration = duration;
    }

    public SuggestSongDtoRequest createSugSongDto(String jsonSong) {
        return new Gson().fromJson(jsonSong, SuggestSongDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (title == null || title.trim().equals("")) {
            throw new RequestException(RequestErrorCode.EMPTY_SONG_TITLE);
        }
        if (composers == null || composers.size() == 0) {
            throw new RequestException(RequestErrorCode.EMPTY_COMPOSER_LIST);
        }
        if (authors == null || authors.size() == 0) {
            throw new RequestException(RequestErrorCode.EMPTY_AUTHOR_LIST);
        }
        if (singer == null || singer.trim().equals("")) {
            throw new RequestException(RequestErrorCode.EMPTY_SINGER_LIST);
        }
        if (duration <= 0) {
            throw new RequestException(RequestErrorCode.WRONG_SONG_DURATION);
        }
    }

    public String getToken() {
        return token;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getComposers() {
        return composers;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public String getSinger() {
        return singer;
    }

    public int getDuration() {
        return duration;
    }

}
