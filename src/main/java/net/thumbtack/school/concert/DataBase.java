package net.thumbtack.school.concert;

import net.thumbtack.school.concert.model.Comment;
import net.thumbtack.school.concert.model.Rating;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.User;

import java.io.*;
import java.util.*;

public class DataBase implements Serializable {

    private static final User WHITE_SAILS_COMMUNITY = new User("WhiteSailsCommunity",
            "WhiteSailsCommunity",
            "WhiteSailsCommunity@gmail.com",
            "Berthollet1785");
    public static final String COMMUNITY_LOGIN = DataBase.WHITE_SAILS_COMMUNITY.getLogin();
    private static final long serialVersionUID = 2487620371369063972L;
    private static DataBase database;
    private Map<String, User> registeredUsers = new HashMap<>();
    private Map<String, String> loggedUsers = new HashMap<>();
    private Map<Integer, Song> songs = new HashMap<>();
    private Integer freeMaxSongId = 1;
    private Map<Integer, Rating> ratings = new HashMap<>();
    private Integer freeMaxRatingId = 1;
    private Map<Integer, Comment> comments = new HashMap<>();
    private Integer freeMaxCommentId = 1;

    private DataBase() {
        registeredUsers.put(DataBase.COMMUNITY_LOGIN, DataBase.WHITE_SAILS_COMMUNITY);
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
        return songs.size();
    }

    public int countComments() {
        return comments.size();
    }

    public String getLoggedUser(String token) {
        return loggedUsers.get(token);
    }


    public boolean isSongSuggested(Song song) {
        return songs.containsValue(song);
    }

    public boolean isSongSuggested(Integer songId) {
        return songs.containsKey(songId);
    }

    public void addSong(Song song) {
        songs.put(song.getSongId(), song);
        freeMaxSongId++;
    }

    public Integer getFreeMaxSongId() {
        return freeMaxSongId;
    }

    public void addSongToUser(String userLogin, Integer songId) {
        registeredUsers.get(userLogin).getSuggestedSongs().add(songId);
    }

    public void addRating(Rating rating) {
        ratings.put(rating.getRatingId(), rating);
        freeMaxRatingId++;
    }

    public Integer getFreeMaxRatingId() {
        return freeMaxRatingId;
    }

    public void addRatedSongToUser(String userLogin, Integer songId, Integer ratingId) {
        registeredUsers.get(userLogin).getRatedSongs().put(songId, ratingId);
    }

    public void addRatingToSong(Integer songId, Integer ratingId) {
        songs.get(songId).getRatings().add(ratingId);
    }

    public boolean isUserSuggestedSong(Integer songId, String userLogin) {
        return registeredUsers.get(userLogin).getSuggestedSongs().contains(songId);
    }

    public boolean isUserRatedSong(String userLogin, Integer songId) {
        return registeredUsers.get(userLogin).getRatedSongs().containsKey(songId);
    }

    public Integer getRatingId(String userLogin, Integer songId) {
        return registeredUsers.get(userLogin).getRatedSongs().get(songId);
    }

    public void changeRating(Integer ratingId, Integer rating) {
        ratings.get(ratingId).setRating(rating);
    }

    public void removeRating(Integer ratingId) {
        ratings.remove(ratingId);
    }

    public void removeRatedSongFromUser(String userLogin, Integer songId) {
        registeredUsers.get(userLogin).getRatedSongs().remove(songId);
    }

    public void removeRatingFromSong(Integer songId, Integer ratingId) {
        songs.get(songId).getRatings().remove(ratingId);
    }

    public boolean isSongNotRated(Integer songId) {
        return songs.get(songId).getRatings().size() == 0;
    }

    public void removeSongFromUser(String userLogin, Integer songId) {
        registeredUsers.get(userLogin).getSuggestedSongs().remove(songId);
    }

    public void changeSuggestedSongUser(Integer songId, String oldUserLogin, String newUserLogin) {
        songs.get(songId).setUserLogin(newUserLogin);
        registeredUsers.get(oldUserLogin).getSuggestedSongs().remove(songId);
        registeredUsers.get(newUserLogin).getSuggestedSongs().add(songId);
    }

    public Integer getFreeMaxCommentId() {
        return freeMaxCommentId;
    }

    public void addComment(Comment comment) {
        comments.put(comment.getCommentId(), comment);
        freeMaxCommentId++;
    }

    public void addCommentToUser(String userLogin, Integer commentId) {
        registeredUsers.get(userLogin).getComments().add(commentId);
    }

    public void addCommentToSong(Integer songId, Integer commentId) {
        songs.get(songId).getComments().add(commentId);
    }

    public boolean isCommentExists(Integer commentId) {
        return comments.containsKey(commentId);
    }

    public boolean isYourComment(String token, Integer commentId) {
        String userLogin = DataBase.getDatabase().getLoggedUser(token);
        return comments.get(commentId).getUserLogin().equals(userLogin);
    }

    public boolean isCommentWithJoins(Integer commentId) {
        return comments.get(commentId).getAgreedUsers().size() > 0;
    }

    public void changeCommentText(Integer commentId, String newCommentText) {
        comments.get(commentId).setText(newCommentText);
    }

    public void changeCommentAuthor(Integer commentId, String oldUserLogin, String newUserLogin) {
        comments.get(commentId).setUserLogin(newUserLogin);
        registeredUsers.get(oldUserLogin).getComments().remove(commentId);
        registeredUsers.get(newUserLogin).getComments().add(commentId);
    }

    public boolean isUserAgreeWithComment(String userLogin, Integer commentId) {
        return comments.get(commentId).getAgreedUsers().contains(userLogin);
    }

    public void agreeWithComment(String userLogin, Integer commentId) {
        comments.get(commentId).getAgreedUsers().add(userLogin);
        registeredUsers.get(userLogin).getAgreedComments().add(commentId);
    }

    public void disagreeWithComment(String userLogin, Integer commentId) {
        comments.get(commentId).getAgreedUsers().remove(userLogin);
        registeredUsers.get(userLogin).getAgreedComments().remove(commentId);
    }

    public List<Song> getConcertSongs() {
        return new ArrayList<>(songs.values());
    }

    public List<Song> getComposerSongs(Set<String> composers) {
        Set<Song> songComposerSet = new HashSet<>();
        Set<Song> songSet = new HashSet<>(songs.values());
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
        Set<Song> songSet = new HashSet<>(songs.values());
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
        Set<Song> songSet = new HashSet<>(songs.values());
        for (Song song : songSet) {
            if (song.getSinger().equals(singer)) {
                songSingerSet.add(song);
            }
        }
        return new ArrayList<>(songSingerSet);
    }

    public Integer getSumSongRating(Integer songId) {
        List<Integer> ratingIds = songs.get(songId).getRatings();
        int sumSongRating = 0;
        for (Integer ratingId : ratingIds) {
            sumSongRating += ratings.get(ratingId).getRating();
        }
        return sumSongRating;
    }

    public List<Map.Entry<Song, Integer>> sortSongsBySumRating() {
        List<Map.Entry<Song, Integer>> sortedSongSumRating = new ArrayList<>();
        Map<Song, Integer> song_sumSongRating = new HashMap<>(songs.size());
        for (Song song : songs.values()) {
            Integer sumSongRating = getSumSongRating(song.getSongId());
            song_sumSongRating.put(song, sumSongRating);
        }
        song_sumSongRating.entrySet().stream().
                sorted(Map.Entry.<Song, Integer>comparingByValue().reversed()).
                forEach(sortedSongSumRating::add);
        return sortedSongSumRating;
    }

    public String getAuthorSuggestedSong(Integer songId) {
        return songs.get(songId).getUserLogin();
    }

    public List<Comment> getSongComments(Song song) {
        List<Integer> songCommentsId = song.getComments();
        List<Comment> songComments = new ArrayList<>(songCommentsId.size());
        for (Integer songCommentId : songCommentsId) {
            songComments.add(comments.get(songCommentId));
        }
        return songComments;
    }

    public int countSongRatings(Song song) {
        return song.getRatings().size();
    }

    public void removeUserSongs(String userLogin) {
        List<Integer> userSongsId = registeredUsers.get(userLogin).getSuggestedSongs();
        List<Integer> removedSongsId = new ArrayList<>();
        for (Integer songId : userSongsId) {
            if (songs.get(songId).getRatings().size() > 1) {
                changeSuggestedSongUser(songId, userLogin, COMMUNITY_LOGIN);
            } else {
                removedSongsId.add(songId);
            }
        }
        for (Integer removedSongId : removedSongsId) {
            removeSong(removedSongId);
        }
    }

    public void removeSong(Integer songId) {
        List<Integer> commentsId = songs.get(songId).getComments();
        for (Integer commentId : commentsId) {
            List<String> agreedUsers = comments.get(commentId).getAgreedUsers();
            for (String agreedUser : agreedUsers) {
                registeredUsers.get(agreedUser).getAgreedComments().remove(commentId);
            }
            String commentAuthor = comments.get(commentId).getUserLogin();
            registeredUsers.get(commentAuthor).getComments().remove(commentId);
            comments.remove(commentId);
        }
        List<Integer> ratingsId = songs.get(songId).getRatings();
        for (Integer ratingId : ratingsId) {
            String ratingAuthor = ratings.get(ratingId).getUserLogin();
            registeredUsers.get(ratingAuthor).getRatedSongs().remove(songId);
            ratings.remove(ratingId);
        }
        String userLogin = songs.get(songId).getUserLogin();
        registeredUsers.get(userLogin).getSuggestedSongs().remove(songId);
        songs.remove(songId);
    }

    public void removeUserRatings(String userLogin) {
        Collection<Integer> ratingsId = registeredUsers.get(userLogin).getRatedSongs().values();
        for (Integer ratingId : ratingsId) {
            Integer songId = ratings.get(ratingId).getSongId();
            songs.get(songId).getRatings().remove(ratingId);
            ratings.remove(ratingId);
        }
    }

    public void removeUserAgrees(String userLogin) {
        List<Integer> agreeCommentsId = registeredUsers.get(userLogin).getAgreedComments();
        for (Integer agreeCommentId : agreeCommentsId) {
            comments.get(agreeCommentId).getAgreedUsers().remove(userLogin);
        }
    }

    public void removeUserComments(String userLogin) {
        List<Integer> userCommentsId = registeredUsers.get(userLogin).getComments();
        for (Integer userCommentId : userCommentsId) {
            List<String> agreedUsers = comments.get(userCommentId).getAgreedUsers();
            for (String agreedUser : agreedUsers) {
                registeredUsers.get(agreedUser).getAgreedComments().remove(userCommentId);
            }
            Integer songId = comments.get(userCommentId).getSongId();
            songs.get(songId).getComments().remove(userCommentId);
            comments.remove(userCommentId);
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


    public boolean isUserLeft(String userLogin) {
        for (Rating rating : ratings.values()) {
            if (rating.getUserLogin().equals(userLogin)) {
                return false;
            }
        }
        for (Comment comment : comments.values()) {
            if (comment.getUserLogin().equals(userLogin)
                    || comment.getAgreedUsers().contains(userLogin)) {
                return false;
            }
        }
        for (Song song : songs.values()){
            if(song.getUserLogin().equals(userLogin)){
                return false;
            }
        }
        return !registeredUsers.keySet().contains(userLogin)
                && !loggedUsers.values().contains(userLogin);
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

    public String getCommentText (Integer commentId) {
       return comments.get(commentId).getText();
    }

    @Override
    public String toString() {
        return "DataBase{" +
                "registeredUsers=" + registeredUsers +
                ", loggedUsers=" + loggedUsers +
                ", songs=" + songs +
                ", freeMaxSongId=" + freeMaxSongId +
                ", ratings=" + ratings +
                ", freeMaxRatingId=" + freeMaxRatingId +
                ", comments=" + comments +
                ", freeMaxCommentId=" + freeMaxCommentId +
                '}';
    }
}
