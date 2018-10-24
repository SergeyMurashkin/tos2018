package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.ArrayList;

public class GetConcertSongsDtoResponse {

    private ArrayList<Song> allConcertSongs;
    private String  error;

    public GetConcertSongsDtoResponse(){
    }

    public GetConcertSongsDtoResponse(ArrayList<Song> allConcertSongs, String  error){
        this.allConcertSongs = allConcertSongs;
        this.error = error;
    }

    public ArrayList<Song> getAllConcertSongs() {
        return allConcertSongs;
    }

    public void setAllConcertSongs(ArrayList<Song> allConcertSongs) {
        this.allConcertSongs = allConcertSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
