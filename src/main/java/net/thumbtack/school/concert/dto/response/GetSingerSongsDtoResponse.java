package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.List;

public class GetSingerSongsDtoResponse {

    private List<Song> singerSongs;
    private String error;

    public GetSingerSongsDtoResponse() {
    }

    public GetSingerSongsDtoResponse(List<Song> singerSongs, String error) {
        this.singerSongs = singerSongs;
        this.error = error;
    }

    public List<Song> getSingerSongs() {
        return singerSongs;
    }

    public void setSingerSongs(List<Song> singerSongs) {
        this.singerSongs = singerSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
