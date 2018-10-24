package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.ArrayList;

public class GetSingerSongsDtoResponse {

    private ArrayList<Song> singerSongs;
    private String  error;

    public GetSingerSongsDtoResponse(){
    }

    public GetSingerSongsDtoResponse(ArrayList<Song> singerSongs, String  error){
        this.singerSongs = singerSongs;
        this.error = error;
    }

    public ArrayList<Song> getSingerSongs() {
        return singerSongs;
    }

    public void setSingerSongs(ArrayList<Song> singerSongs) {
        this.singerSongs = singerSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
