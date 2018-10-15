package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.Song;

public interface SongDao {
    String insert(Song song);
    String remove(Song song);
}


