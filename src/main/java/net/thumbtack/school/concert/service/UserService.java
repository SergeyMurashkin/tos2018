package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import net.thumbtack.school.concert.dto.request.LogoutDtoRequest;
import net.thumbtack.school.concert.dto.response.LoginDtoResponse;
import net.thumbtack.school.concert.model.User;
import net.thumbtack.school.concert.daoimpl.UserDaoImpl;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;

public class UserService {

    public String registerUser(String jsonUser) {
        String jsonCheckedUser = new RegisterUserDtoRequest().createRegUserDto(jsonUser).validate();
        if (jsonCheckedUser.equals("error")) {
            return new Gson().toJson(new RegisterUserDtoResponse(null,"error"),RegisterUserDtoResponse.class);
        }
       User user = new User().createUser(jsonCheckedUser);
        return new UserDaoImpl().insert(user);
    }


    public String logIn(String jsonLogin) {
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest().createLoginDto(jsonLogin);
        String jsonCheckedLogin = loginDtoRequest.validate();
        if (jsonCheckedLogin.equals("error")){
            return new Gson().toJson(new LoginDtoResponse(null,"error"));
        }
        return new UserDaoImpl().logIn(loginDtoRequest.getLogin());
    }


    public String logOut(String jsonLogout) {
        String token = new LogoutDtoRequest().createLogoutDto(jsonLogout).getToken();
        return new UserDaoImpl().logOut(token);
    }
}
