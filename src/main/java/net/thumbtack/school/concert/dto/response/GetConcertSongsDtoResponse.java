package net.thumbtack.school.concert.dto.response;

import net.thumbtack.school.concert.model.Song;

import java.util.List;

public class GetConcertSongsDtoResponse {

    private List<Song> concertSongs;
    private String error;

    public GetConcertSongsDtoResponse() {
    }

    public GetConcertSongsDtoResponse(List<Song> concertSongs, String error) {
        this.concertSongs = concertSongs;
        this.error = error;
    }

    public List<Song> getConcertSongs() {
        return concertSongs;
    }

    public void setConcertSongs(List<Song> concertSongs) {
        this.concertSongs = concertSongs;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
