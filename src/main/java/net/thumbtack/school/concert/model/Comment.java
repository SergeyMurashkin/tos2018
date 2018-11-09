package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comment implements Serializable {

    private static final long serialVersionUID = -334761479774682190L;
    private String userLogin;
    private Integer songId;
    private String text;
    private List<String> agreedUsers;
    private Integer commentId;

    public Comment() {
    }

    public Comment(String userLogin, Integer songId,
                   String text) {
        this.userLogin = userLogin;
        this.songId = songId;
        this.text = text;
        this.agreedUsers = new ArrayList<>();
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAgreedUsers() {
        return agreedUsers;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userLogin='" + userLogin + '\'' +
                ", songId=" + songId +
                ", text='" + text + '\'' +
                ", agreedUsers=" + agreedUsers +
                ", commentId=" + commentId +
                '}';
    }
}
