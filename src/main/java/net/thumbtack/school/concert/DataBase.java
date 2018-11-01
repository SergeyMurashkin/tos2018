package net.thumbtack.school.concert;

import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.User;

import java.io.*;
import java.util.*;

public class DataBase implements Serializable {

    public static final String COMMUNITY_LOGIN = "WhiteSailsCommunity@gmail.com";
    private static final long serialVersionUID = 2487620371369063972L;
    private static DataBase database;
    private Map<String, User> registeredUsers = new HashMap<>();
    private Map<String, String> loggedUsers = new HashMap<>();
    private Map<Song, String> suggestedSong = new HashMap<>();
    private Map<Song, Map<String, Integer>> songRating = new HashMap<>();
    private Map<Song, List<Comment>> comments = new HashMap<>();

    private DataBase() {
    }

    public static DataBase getDatabase() {
        if (database == null) {
            database = new DataBase();
        }
        return database;
    }

    public boolean isUserRegistered(String login) {
        return registeredUsers.containsKey(login);
    }

    public boolean isUserLogged(String token) {
        return loggedUsers.containsKey(token);
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

    public int countSuggestedSong() {
        return suggestedSong.size();
    }

    public String getLoggedUser(String token) {
        return loggedUsers.get(token);
    }

    public String getAuthorSuggestedSong(Song song) {
        return suggestedSong.get(song);
    }

    public void addSong(Song song, String userLogin) {
        suggestedSong.put(song, userLogin);
    }

    public void addRating(Song song, String userLogin, Integer rating) {
        songRating.get(song).put(userLogin, rating);
    }

    public void changeRating(Song song, String userLogin, Integer rating) {
        songRating.get(song).replace(userLogin, rating);
    }

    public boolean isUserRatedSong(Song song, String userLogin) {
        return songRating.get(song).containsKey(userLogin);
    }

    public boolean isSongSuggested(Song song) {
        return suggestedSong.containsKey(song) && songRating.containsKey(song);
    }

    public void addFirstRating(Song song, Map<String, Integer> firstRating) {
        songRating.put(song, firstRating);
    }

    public void removeRating(Song song, String userLogin) {
        songRating.get(song).remove(userLogin);
    }

    public void changeAuthorSuggestedSong(Song song, String oldUserLogin, String newUserLogin) {
        suggestedSong.replace(song, oldUserLogin, newUserLogin);
    }

    public boolean isSongNotRated(Song song) {
        return songRating.get(song).size() == 0;
    }

    public void removeSong(Song song) {
        suggestedSong.remove(song);
        songRating.remove(song);
        comments.remove(song);
    }

    public boolean isUserSuggestedSong(Song song, String token) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        return suggestedSong.get(song).equals(userLogin);
    }

    public List<Song> getConcertSongs() {
        return new ArrayList<>(suggestedSong.keySet());
    }

    public List<Song> getComposerSongs(Set<String> composers) {
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

    public List<Song> getAuthorSongs(Set<String> authors) {
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

    public List<Song> getSingerSongs(String singer) {
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

    public void changeComment(String userLogin, Song song, int commentIndex, String newCommentText) {
        if (comments.get(song).get(commentIndex).getAgreedUsers().size() == 0) {
            comments.get(song).get(commentIndex).setTextComment(newCommentText);
        } else {
            comments.get(song).get(commentIndex).setAuthorComment(DataBase.COMMUNITY_LOGIN);
            int id = DataBase.getDatabase().getMaxCommentId(song);
            comments.get(song).add(new Comment(userLogin, newCommentText, id + 1));
        }
    }

    public List<Comment> getCommentsList(Song song) {
        return comments.get(song);
    }

    public void agreeWithComment(String userLogin, Song song, int commentIndex) {
        if (!comments.get(song).get(commentIndex).getAgreedUsers().contains(userLogin)) {
            comments.get(song).get(commentIndex).getAgreedUsers().add(userLogin);
        } else {
            comments.get(song).get(commentIndex).getAgreedUsers().remove(userLogin);
        }
    }

    public boolean isUserAgreed(Song song, int commentIndex, String userLogin) {
        return comments.get(song).get(commentIndex).getAgreedUsers().contains(userLogin);
    }

    public boolean isCommentExists(Song song, int commentIndex) {
        return comments.get(song).size() > commentIndex;
    }

    public boolean isYourComment(String token, Song song, int commentIndex) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        return comments.get(song).get(commentIndex).getAuthorComment().equals(userLogin);
    }

    public int getMaxCommentId(Song song) {
        if (comments.get(song).size() == 0) {
            return 0;
        } else {
            return comments.get(song).get(comments.get(song).size() - 1).getId();
        }
    }

    public List<Map.Entry<Song, Integer>> sortSongsBySumRating() {
        List<Map.Entry<Song, Integer>> sortedSongSumRating = new ArrayList<>();
        Map<Song, Integer> songSumRating = new HashMap<>(songRating.size());
        for (Map.Entry<Song, Map<String, Integer>> entry : songRating.entrySet()) {
            int sumRating = 0;
            for (HashMap.Entry<String, Integer> elem : entry.getValue().entrySet()) {
                sumRating += elem.getValue();
            }
            songSumRating.put(entry.getKey(), sumRating);
        }
        songSumRating.entrySet().stream().
                sorted(Map.Entry.<Song, Integer>comparingByValue().reversed()).
                forEach(sortedSongSumRating::add);
        return sortedSongSumRating;
    }

    public int countSongRating(Song song) {
        return songRating.get(song).size();
    }

    public List<Song> getAllUserSongs(String userLogin) {
        List<Song> userSongs = new ArrayList<>();
        for (Map.Entry<Song, String> entry : suggestedSong.entrySet()) {
            if (entry.getValue().equals(userLogin)) {
                userSongs.add(entry.getKey());
            }
        }
        return userSongs;
    }

    public void removeUserSongs(List<Song> userSongs, String userLogin) {
        for (Song song : userSongs) {
            if (countSongRating(song) > 1) {
                songRating.get(song).remove(userLogin);
                suggestedSong.replace(song, userLogin, DataBase.COMMUNITY_LOGIN);
            } else {
                removeSong(song);
            }
        }
    }

    public void removeUserCommentsAndAgrees(String userLogin) {
        for (Map.Entry<Song, List<Comment>> entry : comments.entrySet()) {
            List<Comment> removedComments = new ArrayList<>();
            for (Comment comment : entry.getValue()) {
                comment.getAgreedUsers().remove(userLogin);
                if (comment.getAuthorComment().equals(userLogin)) {
                    if (comment.getAgreedUsers().size() > 0) {
                        comment.setAuthorComment(DataBase.COMMUNITY_LOGIN);
                    } else {
                        removedComments.add(comment);
                    }
                }
            }
            comments.get(entry.getKey()).removeAll(removedComments);
        }
    }

    public boolean isUserLeft(String userLogin) {
        for (Map<String, Integer> ratings : songRating.values()) {
            if (ratings.keySet().contains(userLogin)) {
                return false;
            }
        }
        for (List<Comment> songComment : comments.values()) {
            for (Comment comment : songComment) {
                if (comment.getAuthorComment().equals(userLogin)
                        || comment.getAgreedUsers().contains(userLogin)) {
                    return false;
                }
            }
        }
        return !registeredUsers.keySet().contains(userLogin)
                && !loggedUsers.values().contains(userLogin)
                && !suggestedSong.values().contains(userLogin);

    }

    public void removeUserRating(String userLogin) {
        for (Map.Entry<Song, Map<String, Integer>> entry : songRating.entrySet()) {
            songRating.get(entry.getKey()).remove(userLogin);
        }
    }

    public void removeUserTokens(String userLogin) {
        Map<String, String> removedUserTokens = new HashMap<>();
        for (Map.Entry<String, String> entry : loggedUsers.entrySet()) {
            if (entry.getValue().equals(userLogin)) {
                removedUserTokens.put(entry.getKey(), userLogin);
            }
        }
        for (Map.Entry<String, String> entry : removedUserTokens.entrySet()) {
            loggedUsers.remove(entry.getKey(), entry.getValue());
        }
    }

    public void removeUserRegistration(String userLogin) {
        registeredUsers.remove(userLogin);
    }

    public void startDatabase(String savedDataFileName) throws IOException, ClassNotFoundException {
        if (savedDataFileName != null) {
            File serverDB = new File(savedDataFileName);
            try (ObjectInputStream ois = new ObjectInputStream(new DataInputStream(new FileInputStream(serverDB)))) {
                database = (DataBase) ois.readObject();
            }
        } else {
            database = new DataBase();
        }
    }

    public void stopDatabase(String savedDataFileName) throws IOException {
        if (savedDataFileName != null) {
            File serverDB = new File(savedDataFileName);
            try (ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(serverDB)))) {
                oos.writeObject(DataBase.getDatabase());
            }
        }
    }

}
