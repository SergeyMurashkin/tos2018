package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

import net.thumbtack.school.concert.model.Song;

public class AgreeWithCommentDtoRequest {

    private String token;
    private Song song;
    private int commentIndex;

    public AgreeWithCommentDtoRequest() {
    }

    public AgreeWithCommentDtoRequest(String token,
                                      Song song,
                                      int commentIndex) {
        this.token = token;
        this.song = song;
        this.commentIndex = commentIndex;
    }

    public AgreeWithCommentDtoRequest createRequest(String jsonAgreeWithComment) {
        return new Gson().fromJson(jsonAgreeWithComment, AgreeWithCommentDtoRequest.class);
    }


    public String validate() {
        return new Gson().toJson(this,AgreeWithCommentDtoRequest.class);
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

    public int getCommentIndex() {
        return commentIndex;
    }

    public void setCommentIndex(int commentIndex) {
        this.commentIndex = commentIndex;
    }
}
