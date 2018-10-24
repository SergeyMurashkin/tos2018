package net.thumbtack.school.concert.server;

import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.service.SongService;
import net.thumbtack.school.concert.service.UserService;

import java.io.*;

public class Server {

    private UserService userService = new UserService();
    private SongService songService = new SongService();

    public void startServer(String savedDataFileName){
        if (savedDataFileName!=null) {
            File serverDB = new File(savedDataFileName);
            try (ObjectInputStream ois = new ObjectInputStream(new DataInputStream(new FileInputStream(serverDB)))) {
                DataBase dataBase = (DataBase) ois.readObject();
                DataBase.getDatabase().setDatabase(dataBase);
            } catch (IOException e) {
                System.out.println("file not found");
            } catch (ClassNotFoundException e) {
                System.out.println("wrong DataBase");
            }
        }
    }

    public void stopServer(String savedDataFileName) {
        if (savedDataFileName!=null) {
            File serverDB = new File(savedDataFileName);
            try (ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(serverDB)))) {
                oos.writeObject(DataBase.getDatabase());
            } catch (IOException e) {
                System.out.println("file not found");
            }
        }
    }

    public String registerUser(String jsonUser) {
        return userService.registerUser(jsonUser);
    }

    public String logIn(String jsonLogin) {
        return userService.logIn(jsonLogin);
    }

    public String logOut(String jsonLogout) {
        return userService.logOut(jsonLogout);
    }

    public String suggestSong(String jsonSuggest) {
        return songService.suggestSong(jsonSuggest);
    }

    public String addRating(String jsonAddRating) {
        return songService.addRating(jsonAddRating);
    }

    public String removeRating(String jsonRemoveRating) {
        return songService.removeRating(jsonRemoveRating);
    }

    public String addComment(String jsonAddComment){
        return songService.addComment(jsonAddComment);
    }

    public String changeComment(String jsonChangeComment){
        return songService.changeComment(jsonChangeComment);
    }

    public String agreeWithComment( String jsonAgreeWithComment) {
        return songService.agreeWithComment(jsonAgreeWithComment);
    }

    public String getConcertSongs(String jsonGetConcertSongs) {
       return songService.getConcertSongs(jsonGetConcertSongs);
    }

    public String getComposerSongs(String jsonGetComposerSongs) {
        return songService.getComposerSongs(jsonGetComposerSongs);
    }

    public String getAuthorSongs(String jsonGetAuthorSongs) {
        return songService.getAuthorSongs(jsonGetAuthorSongs);
    }

    public String getSingerSongs(String jsonGetSingerSongs) {
        return songService.getSingerSongs(jsonGetSingerSongs);
    }

    public String getTrialConcert(String jsonGetTrialConcert){
        return songService.getTrialConcert(jsonGetTrialConcert);
    }


}
