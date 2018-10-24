package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.ArrayList;

public class GetComposerSongsDtoResponse {

    private ArrayList<Song> composerSongs;
    private String  error;

    public GetComposerSongsDtoResponse(){
    }

    public GetComposerSongsDtoResponse(ArrayList<Song> composerSongs, String  error){
        this.composerSongs = composerSongs;
        this.error = error;
    }

    public ArrayList<Song> getComposerSongs() {
        return composerSongs;
    }

    public void setComposerSongs(ArrayList<Song> composerSongs) {
        this.composerSongs = composerSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
