package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;

import java.util.HashSet;

public interface SongDao {
    String addSong (String token, Song song);
    String addRating (String token, Song song, Integer rating );
    String removeRating (String token, Song song);
    String addComment (String token, Song song, String commentText);
    String changeComment (String token, Song song, int oldCommentIndex, String newCommentText);
    String agreeWithComment (String token, Song song, int commentIndex);
    String getConcertSongs();
    String getComposerSongs(HashSet<String> composer);
    String getAuthorSongs (HashSet<String> author);
    String getSingerSongs (String singer);
    String getTrialConcert();
}


