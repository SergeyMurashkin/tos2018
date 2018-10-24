package net.thumbtack.school.concert.daoimpl;

import com.google.gson.Gson;
import net.thumbtack.school.concert.DataBase;
import net.thumbtack.school.concert.TokenGenerator;
import net.thumbtack.school.concert.dao.UserDao;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;
import net.thumbtack.school.concert.dto.response.LoginDtoResponse;
import net.thumbtack.school.concert.dto.response.LogoutDtoResponse;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;
import net.thumbtack.school.concert.model.User;

public class UserDaoImpl implements UserDao {

    @Override
    public String registerUser(User user) {
        RegisterUserDtoResponse userDtoResponse = new RegisterUserDtoResponse();
        if (DataBase.getDatabase().isUserRegistered(user.getLogin())) {
            userDtoResponse.setError("error: login exists");
        } else {
            DataBase.getDatabase().addUser(user);
            if (DataBase.getDatabase().isUserRegistered(user.getLogin())) {
                String token = new TokenGenerator().generateToken();
                userDtoResponse.setToken(token);
                DataBase.getDatabase().loginUser(token, user.getLogin());
            } else {
                userDtoResponse.setError("error: user not added");
            }
        }
        return new Gson().toJson(userDtoResponse, RegisterUserDtoResponse.class);
    }

    @Override
    public String logIn(String login) {
        String token = new TokenGenerator().generateToken();
        DataBase.getDatabase().loginUser(token, login);
        if (DataBase.getDatabase().isUserLogged(token)) {
            return new Gson().toJson(new LoginDtoResponse(token, null), LoginDtoResponse.class);
        }else{
            return new Gson().toJson(new LoginDtoResponse(null, "error: unsuccessful login"), LoginDtoResponse.class);
        }
    }

    @Override
    public String logOut(String token) {
        DataBase.getDatabase().logoutUser(token);
        if(DataBase.getDatabase().isUserLogged(token)){
            return new Gson().toJson(new LogoutDtoResponse(null,"error: unsuccessful logout"), LogoutDtoResponse.class);
        }else {
            return new Gson().toJson(new LogoutDtoResponse("logout done",null), LogoutDtoResponse.class);
        }
    }


}
