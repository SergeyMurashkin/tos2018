package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.User;

public interface UserDao {

    String registerUser(User user, String token);

    String logIn(String login, String token);

    String logOut(String token);

}
