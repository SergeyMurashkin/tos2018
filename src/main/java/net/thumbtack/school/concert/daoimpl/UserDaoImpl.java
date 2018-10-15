package net.thumbtack.school.concert.daoimpl;

import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.User;
import net.thumbtack.school.concert.dao.UserDao;
import net.thumbtack.school.concert.dto.request.LoginUserDtoRequest;

//class Data Access Object
public class UserDaoImpl implements UserDao {

    @Override
    public String insert(User user) {
       return DataBase.getDatabase().insert(user);
    }

    @Override
    public String logIn(LoginUserDtoRequest loginUserDto) {
        return DataBase.getDatabase().logIn(loginUserDto);
    }

    @Override
    public String logOut(LoginUserDtoRequest logoutUserDto) {
        return DataBase.getDatabase().logOut(logoutUserDto);
    }


}
