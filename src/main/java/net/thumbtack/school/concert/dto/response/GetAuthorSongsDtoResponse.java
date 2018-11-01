package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.List;

public class GetAuthorSongsDtoResponse {

    private List<Song> authorSongs;
    private String error;

    public GetAuthorSongsDtoResponse() {
    }

    public GetAuthorSongsDtoResponse(List<Song> authorSongs, String error) {
        this.authorSongs = authorSongs;
        this.error = error;
    }

    public List<Song> getAuthorSongs() {
        return authorSongs;
    }

    public void setAuthorSongs(List<Song> authorSongs) {
        this.authorSongs = authorSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


}
