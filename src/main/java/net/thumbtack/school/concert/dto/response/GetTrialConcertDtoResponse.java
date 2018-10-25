package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.ArrayList;


public class GetTrialConcertDtoResponse {

    private ArrayList<TrialConcertSong> trialConcertSongs = new ArrayList<>();
    private String error;

    public GetTrialConcertDtoResponse(){
    }

    public GetTrialConcertDtoResponse(ArrayList<TrialConcertSong> trialConcertSongs, String error){
        this.trialConcertSongs = trialConcertSongs;
        this.error = error;
    }

    public ArrayList<TrialConcertSong> getTrialConcertSongs() {
        return trialConcertSongs;
    }

    public void setTrialConcertSongs(ArrayList<TrialConcertSong> trialConcertSongs) {
        this.trialConcertSongs = trialConcertSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
