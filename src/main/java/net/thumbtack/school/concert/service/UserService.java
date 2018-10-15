package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import net.thumbtack.school.concert.User;
import net.thumbtack.school.concert.daoimpl.UserDaoImpl;
import net.thumbtack.school.concert.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;

public class UserService {

    public String registerUser(String jsonUser) {
        String checkedUser = new RegisterUserDtoRequest().createRegUserDto(jsonUser).validate();
        if (checkedUser.equals("error")) {
            return new Gson().toJson(new RegisterUserDtoResponse(checkedUser),RegisterUserDtoResponse.class);
        }
       User user = new User().createUser(checkedUser);
        return new UserDaoImpl().insert(user);
    }


    public String logIn(String jsonLoginInfo) {
        String checkedLogin = new LoginUserDtoRequest().createLoginUserDto(jsonLoginInfo).validate();
        if (checkedLogin.equals("error")){
            return checkedLogin;
        }
        return new UserDaoImpl().logIn(new LoginUserDtoRequest().createLoginUserDto(checkedLogin));
    }


    public String logOut(String jsonLogoutInfo) {
        String checkedLogout = new LoginUserDtoRequest().createLoginUserDto(jsonLogoutInfo).validate();
        if (checkedLogout.equals("error")){
            return checkedLogout;
        }
        return new UserDaoImpl().logOut(new LoginUserDtoRequest().createLoginUserDto(checkedLogout));
    }
}
