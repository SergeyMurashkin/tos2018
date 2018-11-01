package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.List;

public class GetComposerSongsDtoResponse {

    private List<Song> composerSongs;
    private String error;

    public GetComposerSongsDtoResponse() {
    }

    public GetComposerSongsDtoResponse(List<Song> composerSongs, String error) {
        this.composerSongs = composerSongs;
        this.error = error;
    }

    public List<Song> getComposerSongs() {
        return composerSongs;
    }

    public void setComposerSongs(List<Song> composerSongs) {
        this.composerSongs = composerSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
