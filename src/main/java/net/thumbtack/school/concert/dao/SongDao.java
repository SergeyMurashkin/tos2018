package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.Song;

public interface SongDao {
    String insert(Song song);
    String remove(Song song);
}


