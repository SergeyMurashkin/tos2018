package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.dto.request.LoginUserDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestConcert {


    @Test
    public void testRegisterUser() throws IOException, ClassNotFoundException {
        Server server = new Server();
        RegisterUserDtoRequest request = new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "himik@mail.ru", "123456789");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);
        String jsonResponse = server.registerUser(jsonRequest);
        RegisterUserDtoResponse response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("added", response.getToken());
        assertEquals(1, DataBase.getDatabase().getRegisteredUsers().size());

        request = new RegisterUserDtoRequest("Sergei", "Murashkin", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("already exist", response.getToken());
        assertEquals(1, DataBase.getDatabase().getRegisteredUsers().size());

        request = new RegisterUserDtoRequest("Serg", "Murash", "himik2@mail.ru", "987654321");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("added", response.getToken());
        assertEquals(2, DataBase.getDatabase().getRegisteredUsers().size());
        server.stopDB(new File("serverDB.txt"));
        server.startDB(new File("serverDB.txt"));
        assertEquals(2, DataBase.getDatabase().getRegisteredUsers().size());
        assertEquals(2, DataBase.getDatabase().getLogInOutUsers().size());
        assertEquals(false, DataBase.getDatabase().getLogInOutUsers().get("himik@mail.ru"));

        LoginUserDtoRequest loginRequest = new LoginUserDtoRequest("himik@mail.ru", "123456789");
        String login = gson.toJson(loginRequest, LoginUserDtoRequest.class);
        String loginResponse = server.logIn(login);
        assertEquals(true, DataBase.getDatabase().getLogInOutUsers().get("himik@mail.ru"));
        assertEquals("successful", loginResponse);

        LoginUserDtoRequest logoutRequest = new LoginUserDtoRequest("himik@mail.ru", "123456789");
        String logout = gson.toJson(logoutRequest, LoginUserDtoRequest.class);
        String logoutResponse = server.logOut(logout);
        assertEquals(false, DataBase.getDatabase().getLogInOutUsers().get("himik@mail.ru"));
        assertEquals("successful", logoutResponse);

    }

    @Test
    public void testIncorrectRegisterUser() {
        Server server = new Server();
        int before = DataBase.getDatabase().getRegisteredUsers().size();
        RegisterUserDtoRequest request = new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "himik@mail.ru", "12345");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);
        String jsonResponse = server.registerUser(jsonRequest);
        RegisterUserDtoResponse response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getToken());
        assertEquals(before, DataBase.getDatabase().getRegisteredUsers().size());

        before = DataBase.getDatabase().getRegisteredUsers().size();
        request = new RegisterUserDtoRequest(
                "", "Murashkin", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getToken());
        assertEquals(before, DataBase.getDatabase().getRegisteredUsers().size());

        before = DataBase.getDatabase().getRegisteredUsers().size();
        request = new RegisterUserDtoRequest(
                "Sergei", "      ", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getToken());
        assertEquals(before, DataBase.getDatabase().getRegisteredUsers().size());

    }

}
