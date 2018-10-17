package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.model.User;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;

public interface UserDao {
    String insert(User user);
    String logIn(String login);
    String logOut(String token);
}
