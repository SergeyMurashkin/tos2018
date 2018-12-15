package net.thumbtack.school.concert.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.thumbtack.school.concert.TokenGenerator;
import net.thumbtack.school.concert.adapter.SongAdapter;
import net.thumbtack.school.concert.daoimpl.UserDaoImpl;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;
import net.thumbtack.school.concert.dto.request.LogoutDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.LoginDtoResponse;
import net.thumbtack.school.concert.dto.response.LogoutDtoResponse;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.concert.model.Song;
import net.thumbtack.school.concert.model.User;
import net.thumbtack.school.concert.requestException.RequestException;

public class UserService {

    private UserDaoImpl userDaoImpl = new UserDaoImpl();
    private TokenGenerator tokenGenerator = new TokenGenerator();
    private Gson gson = new Gson();
    private RegisterUserDtoRequest registerRequest = new RegisterUserDtoRequest();
    private RegisterUserDtoResponse registerResponse = new RegisterUserDtoResponse();
    private LoginDtoRequest loginRequest = new LoginDtoRequest();
    private LoginDtoResponse loginResponse = new LoginDtoResponse();
    private LogoutDtoRequest logoutRequest = new LogoutDtoRequest();
    private LogoutDtoResponse logoutResponse = new LogoutDtoResponse();

    public String registerUser(String jsonRegisterUser) {
        registerRequest = registerRequest.createRegUserDto(jsonRegisterUser);
        try {
            registerRequest.validate();
            User user = new User(registerRequest.getFirstName(),
                    registerRequest.getLastName(),
                    registerRequest.getLogin(),
                    registerRequest.getPassword());
            String token = tokenGenerator.generateToken();
            String response = userDaoImpl.registerUser(user, token);
            registerResponse.setToken(response);
            registerResponse.setError(null);
        } catch (RequestException ex) {
            registerResponse.setToken(null);
            registerResponse.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(registerResponse, RegisterUserDtoResponse.class);
    }

    public String loginUser(String jsonLogin) {
        loginRequest = loginRequest.createLoginDto(jsonLogin);
        try {
            loginRequest.validate();
            String userLogin = loginRequest.getLogin();
            String password = loginRequest.getPassword();
            String token = tokenGenerator.generateToken();
            String response = userDaoImpl.loginUser(userLogin, password, token);
            loginResponse.setToken(response);
            loginResponse.setError(null);
        } catch (RequestException ex) {
            loginResponse.setToken(null);
            loginResponse.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(loginResponse, LoginDtoResponse.class);
    }

    public String logoutUser(String jsonLogout) {
        String token = logoutRequest.createLogoutDto(jsonLogout).getToken();
        try {
            String response = userDaoImpl.logoutUser(token);
            logoutResponse.setResponse(response);
            logoutResponse.setError(null);
        } catch (RequestException ex) {
            logoutResponse.setResponse(null);
            logoutResponse.setError(ex.getRequestErrorCode().getErrorString());
        }
        return gson.toJson(logoutResponse, LogoutDtoResponse.class);
    }

}
