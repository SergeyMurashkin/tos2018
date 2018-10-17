package net.thumbtack.school.concert.service;

import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.daoimpl.SongDaoImpl;
import net.thumbtack.school.concert.dto.request.SuggestSongDtoRequest;

public class SongService {

    public String suggestSong(String jsonSong) {
        String checkedSong = new SuggestSongDtoRequest().createSugSongDto(jsonSong).validate();
        if (checkedSong.equals("error")) {
            return checkedSong;
        }
        Song song = new Song().createSong(checkedSong);
        return new SongDaoImpl().insert(song);
    }
}
