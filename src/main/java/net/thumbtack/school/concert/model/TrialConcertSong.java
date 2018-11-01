package net.thumbtack.school.concert.model;

import java.util.List;

public class TrialConcertSong {

    private Song song;
    private String userLogin;
    private double averageRating;
    private List<Comment> allSongComments;

    public TrialConcertSong() {
    }

    public TrialConcertSong(
            Song song,
            String userLogin,
            double averageRating,
            List<Comment> allSongComments) {

        this.song = song;
        this.userLogin = userLogin;
        this.averageRating = averageRating;
        this.allSongComments = allSongComments;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
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

}
