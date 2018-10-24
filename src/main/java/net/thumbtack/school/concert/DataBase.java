package net.thumbtack.school.concert;

import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.User;

import java.io.Serializable;
import java.util.*;

public class DataBase implements Serializable {

    private static final long serialVersionUID = 2487620371369063972L;
    private static DataBase database;
    private Map<String, User> registeredUsers = new HashMap<>();
    private Map<String, String> loggedUsers = new HashMap<>();
    private Map<Song, String> suggestedSong = new HashMap<>();
    private Map<Song, HashMap<String, Integer>> songRating = new HashMap<>();
 //   private Map<Song, Integer> sumSongRating = new HashMap<>();
    private Map<Song, ArrayList<Comment>> comments = new HashMap<>();

    private DataBase() {
    }


    public static DataBase getDatabase() {
        if (database == null) {
            database = new DataBase();
        }
        return database;
    }


    public void setDatabase(DataBase database) {
        DataBase.database = database;
    }

    public boolean isUserRegistered(String login) {
        return registeredUsers.containsKey(login);
    }

    public boolean isPasswordRight(String login, String password) {
        return registeredUsers.get(login).getPassword().equals(password);
    }

    public void addUser(User user) {
        registeredUsers.put(user.getLogin(), user);
    }

    public void loginUser(String token, String login) {
        loggedUsers.put(token, login);
    }

    public void logoutUser(String token) {
        loggedUsers.remove(token);
    }

    public int countRegisteredUsers() {
        return registeredUsers.size();
    }

    public int countLoggedUsers() {
        return loggedUsers.size();
    }

    public boolean isUserLogged(String token) {
        return loggedUsers.containsKey(token);
    }

    public String getLoggedUser(String token) {
        return loggedUsers.get(token);
    }

    public String getAuthorSuggestedSong(Song song) {
        return suggestedSong.get(song);
    }

    public void addSong(Song song, String authorRequest) {
        suggestedSong.put(song, authorRequest);
    }


    public void addRating(Song song, String authorRequest, Integer rating) {
        songRating.get(song).put(authorRequest, rating);
    }

    public void changeRating(Song song, String authorRequest, Integer rating) {
        songRating.get(song).replace(authorRequest, rating);
    }


    public boolean isUserRatedSong(Song song, String authorRequest) {
        return songRating.get(song).containsKey(authorRequest);
    }

    public boolean isSongSuggested(Song song) {
        return suggestedSong.containsKey(song) && songRating.containsKey(song);
    }

    public void addFirstRating(Song song, HashMap<String, Integer> firstRating) {
        songRating.put(song, firstRating);
    }

    public void removeRating(Song song, String authorRequest) {
        songRating.get(song).remove(authorRequest);
    }

    public void changeAuthorSuggestedSong(Song song, String oldAuthorRequest) {
        suggestedSong.replace(song, oldAuthorRequest, "userCommunity@gmail.com");
    }


    public boolean isSongNotRated(Song song) {
        return songRating.get(song).size() == 0;
    }

    public void removeSong(Song song) {
        suggestedSong.remove(song);
        songRating.remove(song);
        comments.remove(song);
    }


    public boolean isYourSuggestedSong(Song song, String token) {
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        return suggestedSong.get(song).equals(authorRequest);
    }

    public int countSuggestedSong() {
        return suggestedSong.size();
    }

    public ArrayList<Song> getConcertSongs() {
        Set<Song> songSet = new HashSet<>(suggestedSong.keySet());
        return new ArrayList<>(songSet);
    }

    public ArrayList<Song> getComposerSongs(HashSet<String> composers) {
        Set<Song> songComposerSet = new HashSet<>();
        Set<Song> songSet = new HashSet<>(suggestedSong.keySet());
        for (Song song : songSet) {
            for (String composer : composers) {
                if (song.getComposer().contains(composer)) {
                    songComposerSet.add(song);
                }
            }
        }
        return new ArrayList<>(songComposerSet);
    }

    public ArrayList<Song> getAuthorSongs(HashSet<String> authors) {
        Set<Song> songAuthorSet = new HashSet<>();
        Set<Song> songSet = new HashSet<>(suggestedSong.keySet());
        for (Song song : songSet) {
            for (String author : authors) {
                if (song.getAuthor().contains(author)) {
                    songAuthorSet.add(song);
                }
            }
        }
        return new ArrayList<>(songAuthorSet);
    }


    public ArrayList<Song> getSingerSongs(String singer) {
        Set<Song> songSingerSet = new HashSet<>();
        Set<Song> songSet = new HashSet<>(suggestedSong.keySet());
        for (Song song : songSet) {
            if (song.getSinger().equals(singer)) {
                songSingerSet.add(song);
            }
        }
        return new ArrayList<>(songSingerSet);
    }


    public boolean isSongCommented(Song song) {
        return comments.containsKey(song);
    }

    public void addComment(Song song, Comment comment) {
        comments.get(song).add(comment);
    }

    public void addFirstComment(Song song, Comment comment) {
        comments.put(song, new ArrayList<>());
        comments.get(song).add(comment);
    }


    public void changeComment(String authorRequest, Song song, int commentIndex, String newCommentText) {
        if (comments.get(song).get(commentIndex).getAgreedUsers().size() == 0) {
            comments.get(song).get(commentIndex).setTextComment(newCommentText);
        } else {
            comments.get(song).get(commentIndex).setAuthorComment("userCommunity@gmail.com");
            int id = DataBase.getDatabase().getMaxCommentId(song);
            comments.get(song).add(new Comment(authorRequest, newCommentText, id + 1));
        }

    }

    public ArrayList<Comment> getCommentsList(Song song) {
        return comments.get(song);
    }


    public void agreeWithComment(String authorRequest, Song song, int commentIndex) {
        if (!comments.get(song).get(commentIndex).getAgreedUsers().contains(authorRequest)) {
            comments.get(song).get(commentIndex).getAgreedUsers().add(authorRequest);
        } else {
            comments.get(song).get(commentIndex).getAgreedUsers().remove(authorRequest);
        }
    }

    public boolean areUsersAgreed(Song song, Comment comment) {
        int index = comments.get(song).indexOf(comment);
        return comments.get(song).get(index).getAgreedUsers().size() > 0;
    }

    public boolean isUserAgreed(Song song, int commentIndex, String authorRequest) {
        return comments.get(song).get(commentIndex).getAgreedUsers().contains(authorRequest);
    }


    public boolean isCommentExists(Song song, Comment comment) {
        return comments.get(song).contains(comment);
    }

    public boolean isCommentExists(Song song, int commentIndex) {
        return comments.get(song).size() > commentIndex;
    }


    public boolean isYourComment(String token, Comment comment) {
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        return comment.getAuthorComment().equals(authorRequest);
    }

    public boolean isYourComment(String token, Song song, int commentIndex) {
        String authorRequest = DataBase.getDatabase().getLoggedUser(token);
        return comments.get(song).get(commentIndex).getAuthorComment().equals(authorRequest);
    }


    public int getMaxCommentId(Song song) {
        if (comments.get(song).size() == 0) {
            return 0;
        } else {
            return comments.get(song).get(comments.get(song).size() - 1).getId();
        }
    }

/*
    public void getSortedSongs() {
        Map<Song, Integer> sortedSongSumRating = new HashMap<>(songRating.size());
        for (Map.Entry<Song, HashMap<String, Integer>> entry : songRating.entrySet()) {
            int sumRating = 0;
            for (HashMap.Entry<String, Integer> elem : entry.getValue().entrySet()) {
                sumRating += elem.getValue();
            }
            sortedSongSumRating.put(entry.getKey(), sumRating);
        }
        sortedSongSumRating.entrySet().stream().
                sorted(Map.Entry.<Song, Integer>comparingByValue().reversed()).
                collect();
    }*/


}
