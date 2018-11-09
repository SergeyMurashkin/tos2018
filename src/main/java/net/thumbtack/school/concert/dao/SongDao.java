package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.TrialConcertSong;

import java.util.List;
import java.util.Set;

public interface SongDao {

    String addSong(String token, Song song);

    String addRating(String token, Integer songId, Integer rating);

    String removeRating(String token, Integer songId);

    String addComment(String token, Integer songId, String commentText);

    String changeComment(String token, Integer songId, Integer commentId, String newCommentText);

    String agreeWithComment(String token, Integer commentIndex);

    List<Song> getConcertSongs();

    List<Song> getComposerSongs(Set<String> composer);

    List<Song> getAuthorSongs(Set<String> author);

    List<Song> getSingerSongs(String singer);

    List<TrialConcertSong> getTrialConcert();

    String leaveServer(String token);

}


