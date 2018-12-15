package net.thumbtack.school.concert.model;

import java.io.Serializable;
import java.util.List;

public class TrialConcertSong implements Serializable {

    private static final long serialVersionUID = -923480987644278581L;
    private Song song;
    private Integer userId;
    private double averageRating;
    private List<Comment> allSongComments;

    public TrialConcertSong() {
    }

    public TrialConcertSong(
            Song song,
            Integer userId,
            double averageRating,
            List<Comment> allSongComments) {
        this.song = song;
        this.userId = userId;
        this.averageRating = averageRating;
        this.allSongComments = allSongComments;
    }

    public TrialConcertSong(
            Song song,
            double averageRating) {
        this.song = song;
        this.userId = song.getUserId();
        this.averageRating = averageRating;
        this.allSongComments = song.getComments();
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUser(Integer userId) {
        this.userId = userId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Comment> getAllSongComments() {
        return allSongComments;
    }

    public void setAllSongComments(List<Comment> allSongComments) {
        this.allSongComments = allSongComments;
    }

    @Override
    public String toString() {
        return "TrialConcertSong{" +
                "song=" + song +
                ", userId=" + userId +
                ", averageRating=" + averageRating +
                ", allSongComments=" + allSongComments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrialConcertSong)) return false;

        TrialConcertSong that = (TrialConcertSong) o;

        if (Double.compare(that.getAverageRating(), getAverageRating()) != 0) return false;
        if (getSong() != null ? !getSong().equals(that.getSong()) : that.getSong() != null) return false;
        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        return getAllSongComments() != null ? getAllSongComments().equals(that.getAllSongComments()) : that.getAllSongComments() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getSong() != null ? getSong().hashCode() : 0;
        result = 31 * result + (getUserId() != null ? getUserId().hashCode() : 0);
        temp = Double.doubleToLongBits(getAverageRating());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getAllSongComments() != null ? getAllSongComments().hashCode() : 0);
        return result;
    }

}
