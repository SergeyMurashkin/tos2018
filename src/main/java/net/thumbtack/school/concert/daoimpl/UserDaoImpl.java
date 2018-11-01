package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.dao.UserDao;
import net.thumbtack.school.concert.model.User;

public class UserDaoImpl implements UserDao {

    @Override
    public String registerUser(User user, String token) {
        if (DataBase.getDatabase().isUserRegistered(user.getLogin())
                || user.getLogin().equals(DataBase.COMMUNITY_LOGIN)) {
            return "error: login exists";
        } else {
            DataBase.getDatabase().addUser(user);
            if (DataBase.getDatabase().isUserRegistered(user.getLogin())) {
                DataBase.getDatabase().loginUser(token, user.getLogin());
                return token;
            } else {
                return "error: user not added";
            }
        }
    }

    @Override
    public String logIn(String login, String token) {
        DataBase.getDatabase().loginUser(token, login);
        if (DataBase.getDatabase().isUserLogged(token)) {
            return token;
        } else {
            return "error: unsuccessful login";
        }
    }

    @Override
    public String logOut(String token) {
        DataBase.getDatabase().logoutUser(token);
        if (DataBase.getDatabase().isUserLogged(token)) {
            return "error: unsuccessful logout";
        } else {
            return "logout done";
        }
    }

}
