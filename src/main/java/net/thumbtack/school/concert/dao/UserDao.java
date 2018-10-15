package net.thumbtack.school.concert.dao;

import net.thumbtack.school.concert.User;
import net.thumbtack.school.concert.dto.request.LoginUserDtoRequest;

public interface UserDao {
    String insert(User user);
    String logIn(LoginUserDtoRequest loginUserDto);
    String logOut(LoginUserDtoRequest logoutUserDto);
}
