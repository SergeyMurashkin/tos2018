package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;

import java.util.HashSet;

public class SuggestSongDtoRequest {

    private String token;
    private String title;
    private HashSet<String> composer;
    private HashSet<String> author;
    private String singer;
    private int duration;


    public SuggestSongDtoRequest() {
    }

    public SuggestSongDtoRequest(String token,
                                 String title,
                                 HashSet<String> composer,
                                 HashSet<String> author,
                                 String singer,
                                 int duration) {
        this.token = token;
        this.title = title;
        this.composer = composer;
        this.author = author;
        this.singer = singer;
        this.duration = duration;
    }

    public SuggestSongDtoRequest createSugSongDto(String jsonSong) {
        return new Gson().fromJson(jsonSong, SuggestSongDtoRequest.class);
    }

    public String validate() {
        if (!DataBase.getDatabase().isUserLogged(token)) {
            return "error: please login";
        }
        if (title == null || title.trim().equals("")) {
            return "error: empty song title ";
        }
        if (composer == null || composer.size() == 0) {
            return "error: empty composer list";
        }
        if (author == null || author.size() == 0) {
            return "error: empty author list";
        }
        if (singer == null || singer.trim().equals("")) {
            return "error: empty singer list";
        }
        if (duration <= 0) {
            return "error: negative time";
        } else {
            return new Gson().toJson(this, SuggestSongDtoRequest.class);
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashSet<String> getComposer() {
        return composer;
    }

    public void setComposer(HashSet<String> composer) {
        this.composer = composer;
    }

    public HashSet<String> getAuthor() {
        return author;
    }

    public void setAuthor(HashSet<String> author) {
        this.author = author;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
