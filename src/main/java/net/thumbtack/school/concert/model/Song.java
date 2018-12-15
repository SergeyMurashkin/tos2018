package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Song implements Serializable {

    private static final long serialVersionUID = 1653987098914607270L;
    private Integer id;
    private String title;
    private List<String> composers;
    private List<String> authors;
    private String singer;
    private Integer duration;
    private Integer userId;
    private List<Rating> ratings;
    private List<Comment> comments;

    public Song() {
    }

    public Song(Integer id,
                String title,
                List<String> composers,
                List<String> authors,
                String singer,
                Integer duration,
                Integer userId,
                List<Rating> ratings,
                List<Comment> comments) {
        this();
        this.id = id;
        this.title = title.trim();
        this.composers = composers;
        this.authors = authors;
        this.singer = singer.trim();
        this.duration = duration;
        this.userId = userId;
        this.ratings = ratings;
        this.comments = comments;
    }

    public Song(String title,
                List<String> composers,
                List<String> authors,
                String singer,
                Integer duration) {
        this(0, title, composers, authors, singer, duration,null, new ArrayList<>(), new ArrayList<>());
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getComposers() {
        return composers;
    }

    public void setComposers(List<String> composers) {
        this.composers = composers;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", composers=" + composers +
                ", authors=" + authors +
                ", singer='" + singer + '\'' +
                ", duration=" + duration +
                ", userId=" + userId +
                ", ratings=" + ratings +
                ", comments=" + comments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song)) return false;

        Song song = (Song) o;

        if (getTitle() != null ? !getTitle().equals(song.getTitle()) : song.getTitle() != null) return false;
        return getSinger() != null ? getSinger().equals(song.getSinger()) : song.getSinger() == null;
    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getSinger() != null ? getSinger().hashCode() : 0);
        return result;
    }
}
