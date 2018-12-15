package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Comment implements Serializable {

    private static final long serialVersionUID = -334761479774682190L;
    private Integer id;
    private Integer songId;
    private Integer userId;
    private String text;
    private Set<Integer> agreedUsersId;

    public Comment() {
    }

    public Comment(Integer id, Integer songId, Integer userId,
                   String text, Set<Integer> agreedUsersId) {
        this();
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.text = text;
        this.agreedUsersId = agreedUsersId;
    }

    public Comment(Integer id, Integer songId, Integer userId,
                   String text) {
        this(id, songId, userId, text, new HashSet<>());
    }

    public Comment(Integer songId, Integer userId,
                   String text) {
        this(0, songId, userId, text, new HashSet<>());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public Set<Integer> getAgreedUsersId() {
        return agreedUsersId;
    }

    public void setAgreedUsers(Set<Integer> agreedUsersId) {
        this.agreedUsersId = agreedUsersId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", songId=" + songId +
                ", text='" + text + '\'' +
                ", agreedUsersId=" + agreedUsersId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;

        Comment comment = (Comment) o;

        if (getId() != null ? !getId().equals(comment.getId()) : comment.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(comment.getUserId()) : comment.getUserId() != null) return false;
        if (getSongId() != null ? !getSongId().equals(comment.getSongId()) : comment.getSongId() != null) return false;
        if (getText() != null ? !getText().equals(comment.getText()) : comment.getText() != null) return false;
        return getAgreedUsersId() != null ? getAgreedUsersId().equals(comment.getAgreedUsersId()) : comment.getAgreedUsersId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getSongId() != null ? getSongId().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getAgreedUsersId() != null ? getAgreedUsersId().hashCode() : 0);
        return result;
    }
}
