package net.thumbtack.school.concert.model;

import java.io.Serializable;

public class Rating implements Serializable {

    private static final long serialVersionUID = 6868792766299052156L;
    private Integer id;
    private Integer userId;
    private Integer songId;
    private Integer rating;

    public Rating() {
    }

    public Rating(Integer id,
                  Integer userId,
                  Integer songId,
                  Integer rating) {
        this();
        this.id = id;
        this.userId = userId;
        this.songId = songId;
        this.rating = rating;
    }

    public Rating(Integer userId,
                  Integer songId,
                  Integer rating) {
        this(0, userId, songId, rating);
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", songId=" + songId +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;

        Rating rating1 = (Rating) o;

        if (getId() != null ? !getId().equals(rating1.getId()) : rating1.getId() != null) return false;
        if (getUserId() != null ? !getUserId().equals(rating1.getUserId()) : rating1.getUserId() != null) return false;
        if (getSongId() != null ? !getSongId().equals(rating1.getSongId()) : rating1.getSongId() != null) return false;
        return getRating() != null ? getRating().equals(rating1.getRating()) : rating1.getRating() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        result = 31 * result + (getSongId() != null ? getSongId().hashCode() : 0);
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        return result;
    }
}
