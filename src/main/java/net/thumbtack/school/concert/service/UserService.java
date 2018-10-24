package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import net.thumbtack.school.concert.daoimpl.UserDaoImpl;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;
import net.thumbtack.school.concert.dto.request.LogoutDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.LoginDtoResponse;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.concert.model.User;

public class UserService {

    public String registerUser(String jsonRegisterUser) {
        RegisterUserDtoRequest registerUser = new RegisterUserDtoRequest().createRegUserDto(jsonRegisterUser);
        String jsonCheckedRequest = registerUser.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new RegisterUserDtoResponse(null, jsonCheckedRequest), RegisterUserDtoResponse.class);
        }
        User user = new User(registerUser.getFirstName(),
                registerUser.getLastName(),
                registerUser.getLogin(),
                registerUser.getPassword());
        return new UserDaoImpl().registerUser(user);
    }


    public String logIn(String jsonLogin) {
        LoginDtoRequest loginDtoRequest = new LoginDtoRequest().createLoginDto(jsonLogin);
        String jsonCheckedRequest = loginDtoRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            return new Gson().toJson(new LoginDtoResponse(null, jsonCheckedRequest),LoginDtoResponse.class);
        }
        return new UserDaoImpl().logIn(loginDtoRequest.getLogin());
    }


    public String logOut(String jsonLogout) {
        String token = new LogoutDtoRequest().createLogoutDto(jsonLogout).getToken();
        return new UserDaoImpl().logOut(token);
    }
}
