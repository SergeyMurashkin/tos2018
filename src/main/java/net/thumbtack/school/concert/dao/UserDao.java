package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.User;
import net.thumbtack.school.concert.requestException.RequestException;

public interface UserDao {

    String registerUser(User user, String token) throws RequestException;

    String loginUser(String login, String  password, String token) throws RequestException;

    String logoutUser(String token) throws RequestException;

    Integer getUserIdByToken(String token) throws RequestException;

    User getUserById(Integer userId) throws RequestException;

}
