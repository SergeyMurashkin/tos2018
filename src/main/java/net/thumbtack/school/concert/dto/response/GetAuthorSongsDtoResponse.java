package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.ArrayList;

public class GetAuthorSongsDtoResponse {

    private ArrayList<Song> authorSongs;
    private String  error;

    public GetAuthorSongsDtoResponse(){
    }

    public GetAuthorSongsDtoResponse(ArrayList<Song> authorSongs, String  error){
        this.authorSongs = authorSongs;
        this.error = error;
    }

    public ArrayList<Song> getAuthorSongs() {
        return authorSongs;
    }

    public void setAuthorSongs(ArrayList<Song> authorSongs) {
        this.authorSongs = authorSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
