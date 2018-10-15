package net.thumbtack.school.concert;

import net.thumbtack.school.concert.service.SongService;
import net.thumbtack.school.concert.service.UserService;

import java.io.*;

public class Server {

    private UserService userService = new UserService();
    private SongService songService = new SongService();
    private File serverDB = new File("serverDB.txt");

    public String registerUser(String jsonUser) {
        return userService.registerUser(jsonUser);
    }

    public String logIn(String jsonUserInfo) {
        return userService.logIn(jsonUserInfo);
    }

    public String logOut(String jsonUserInfo) {
        return userService.logOut(jsonUserInfo);
    }

    public void stopDB(File file) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new DataOutputStream(new FileOutputStream(file)))) {
            oos.writeObject(DataBase.getDatabase());
        }
    }

    public void startDB(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new DataInputStream(new FileInputStream(file)))) {
            DataBase dataBase = (DataBase) ois.readObject();
            DataBase.getDatabase().setRegisteredUsers(dataBase.getRegisteredUsers());
            DataBase.getDatabase().setLogInOutUsers(dataBase.getLogInOutUsers());

        }
    }

    public String suggestSong (String jsonString) {
       return songService.suggestSong(jsonString);
    }


}
