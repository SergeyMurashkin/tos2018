package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import net.thumbtack.school.concert.TokenGenerator;
import net.thumbtack.school.concert.daoimpl.UserDaoImpl;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;
import net.thumbtack.school.concert.dto.request.LogoutDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.LoginDtoResponse;
import net.thumbtack.school.concert.dto.response.LogoutDtoResponse;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.concert.model.User;

public class UserService {

    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private TokenGenerator tokenGenerator = new TokenGenerator();
    private RegisterUserDtoRequest registerRequest = new RegisterUserDtoRequest();
    private RegisterUserDtoResponse registerResponse = new RegisterUserDtoResponse();
    private LoginDtoRequest loginRequest = new LoginDtoRequest();
    private LoginDtoResponse loginResponse = new LoginDtoResponse();
    private LogoutDtoRequest logoutRequest = new LogoutDtoRequest();
    private LogoutDtoResponse logoutResponse = new LogoutDtoResponse();

    public String registerUser(String jsonRegisterUser) {
        registerRequest = registerRequest.createRegUserDto(jsonRegisterUser);
        String jsonCheckedRequest = registerRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            registerResponse.setToken(null);
            registerResponse.setError(jsonCheckedRequest);
        } else {
            User user = new User(registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getLogin(),
                    registerRequest.getPassword());
            String token = tokenGenerator.generateToken();
            String response = userDaoImpl.registerUser(user, token);
            if (response.contains("error:")) {
                registerResponse.setToken(null);
                registerResponse.setError(response);
            } else {
                registerResponse.setToken(response);
                registerResponse.setError(null);
            }
        }
        return new Gson().toJson(registerResponse, RegisterUserDtoResponse.class);
    }

    public String logIn(String jsonLogin) {
        loginRequest = loginRequest.createLoginDto(jsonLogin);
        String jsonCheckedRequest = loginRequest.validate();
        if (jsonCheckedRequest.contains("error:")) {
            loginResponse.setToken(null);
            loginResponse.setError(jsonCheckedRequest);
        }
        String userLogin = loginRequest.getLogin();
        String token = tokenGenerator.generateToken();
        String response = userDaoImpl.logIn(userLogin, token);
        if (response.contains("error:")) {
            loginResponse.setToken(null);
            loginResponse.setError(response);
        } else {
            loginResponse.setToken(response);
            loginResponse.setError(null);
        }
        return new Gson().toJson(loginResponse, LoginDtoResponse.class);
    }

    public String logOut(String jsonLogout) {
        String token = logoutRequest.createLogoutDto(jsonLogout).getToken();
        String response = userDaoImpl.logOut(token);
        if (response.contains("error:")) {
            logoutResponse.setResponse(null);
            logoutResponse.setError(response);
        } else {
            logoutResponse.setResponse(response);
            logoutResponse.setError(null);
        }
        return new Gson().toJson(logoutResponse, LogoutDtoResponse.class);
    }

}
