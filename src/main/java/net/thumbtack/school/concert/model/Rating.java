package net.thumbtack.school.concert.model;

public class Rating {

    private String userLogin;
    private Integer songId;
    private Integer rating;
    private Integer ratingId;

    public Rating() {
    }

    public Rating(String userLogin,
                  Integer songId,
                  Integer rating) {
        this.userLogin = userLogin;
        this.songId = songId;
        this.rating = rating;
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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "userLogin='" + userLogin + '\'' +
                ", songId=" + songId +
                ", rating=" + rating +
                ", ratingId=" + ratingId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (userLogin != null ? !userLogin.equals(rating.userLogin) : rating.userLogin != null) return false;
        return songId != null ? songId.equals(rating.songId) : rating.songId == null;
    }

    @Override
    public int hashCode() {
        int result = userLogin != null ? userLogin.hashCode() : 0;
        result = 31 * result + (songId != null ? songId.hashCode() : 0);
        return result;
    }
}
