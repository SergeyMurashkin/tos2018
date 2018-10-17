package net.thumbtack.school.concert;

import net.thumbtack.school.concert.model.User;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DataBase implements Serializable {

    private static final long serialVersionUID = 2487620371369063972L;
    private static DataBase database;
    private Map<String, User> registeredUsers = new HashMap<>();
    private Map<String, String> loginUsers = new HashMap<>();

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
        loginUsers.put(token, login);
    }

    public void logoutUser(String token) {
        loginUsers.remove(token);
    }

    public int countRegisteredUsers() {
        return registeredUsers.size();
    }

    public int countLoggedUsers() {
        return loginUsers.size();
    }

    public boolean isUserLogged(String token) {
        return loginUsers.containsKey(token);
    }
}
