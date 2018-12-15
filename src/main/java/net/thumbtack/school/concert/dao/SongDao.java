package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;
import net.thumbtack.school.concert.requestException.RequestException;

import java.util.List;
import java.util.Set;

public interface SongDao {

    String addSong(String token, Song song) throws RequestException;

    String addRating(String token, Integer songId, Integer rating) throws RequestException;

    String removeRating(String token, Integer songId) throws RequestException;

    String addComment(String token, Integer songId, String commentText) throws RequestException;

    String changeComment(String token, Integer commentId, String newCommentText) throws RequestException;

    String agreeWithComment(String token, Integer commentIndex) throws RequestException;

    Song getSongById (Integer songId) throws RequestException;

    List<Song> getConcertSongs(String token) throws RequestException;

    List<Song> getComposerSongs(String token , Set<String> composers) throws RequestException;

    List<Song> getAuthorSongs(String token, Set<String> authors) throws RequestException;

    List<Song> getSingerSongs(String  token, String singer) throws RequestException;

    List<TrialConcertSong> getTrialConcert(String token) throws RequestException;

    String leaveServer(String token) throws RequestException;

}


