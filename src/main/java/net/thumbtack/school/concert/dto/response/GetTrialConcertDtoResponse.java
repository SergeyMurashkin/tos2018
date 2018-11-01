package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.List;


public class GetTrialConcertDtoResponse {

    private List<TrialConcertSong> trialConcertSongs;
    private String error;

    public GetTrialConcertDtoResponse() {
    }

    public GetTrialConcertDtoResponse(List<TrialConcertSong> trialConcertSongs, String error) {
        this.trialConcertSongs = trialConcertSongs;
        this.error = error;
    }

    public List<TrialConcertSong> getTrialConcertSongs() {
        return trialConcertSongs;
    }

    public void setTrialConcertSongs(List<TrialConcertSong> trialConcertSongs) {
        this.trialConcertSongs = trialConcertSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
