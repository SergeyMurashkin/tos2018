package net.thumbtack.school.concert;

import com.google.gson.Gson;
import net.thumbtack.school.concert.dto.request.LoginDtoRequest;
import net.thumbtack.school.concert.dto.request.LogoutDtoRequest;
import net.thumbtack.school.concert.dto.request.RegisterUserDtoRequest;
import net.thumbtack.school.concert.dto.response.LoginDtoResponse;
import net.thumbtack.school.concert.dto.response.RegisterUserDtoResponse;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

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
        assertTrue(DataBase.getDatabase().isUserLogged(response.getToken()));
        assertEquals(1, DataBase.getDatabase().countRegisteredUsers());

        request = new RegisterUserDtoRequest("Sergei", "Murashkin", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getError());
        assertEquals(1, DataBase.getDatabase().countRegisteredUsers());

        request = new RegisterUserDtoRequest("Serg", "Murash", "himik2@mail.ru", "987654321");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertTrue(DataBase.getDatabase().isUserLogged(response.getToken()));
        assertEquals(2, DataBase.getDatabase().countRegisteredUsers());
        server.stopDB(new File("serverDB.txt"));
        server.startDB(new File("serverDB.txt"));
        assertEquals(2, DataBase.getDatabase().countRegisteredUsers());
        assertEquals(2, DataBase.getDatabase().countLoggedUsers());

        LogoutDtoRequest logoutRequest = new LogoutDtoRequest(response.getToken());
        String jsonLogoutRequest = new Gson().toJson(logoutRequest, LogoutDtoRequest.class);
        server.logOut(jsonLogoutRequest);
        assertFalse(DataBase.getDatabase().isUserLogged(response.getToken()));

        LoginDtoRequest loginRequest = new LoginDtoRequest("himik@mail.ru", "123456789");
        String login = gson.toJson(loginRequest, LoginDtoRequest.class);
        String jsonLoginResponse = server.logIn(login);
        String token = new Gson().fromJson(jsonLoginResponse, LoginDtoResponse.class).getToken();
        assertTrue(DataBase.getDatabase().isUserLogged(token));

    }

    @Test
    public void testIncorrectRegisterUser() {
        Server server = new Server();
        int before = DataBase.getDatabase().countRegisteredUsers();
        RegisterUserDtoRequest request = new RegisterUserDtoRequest(
                "Sergei", "Murashkin", "himik@mail.ru", "12345");
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(request);
        String jsonResponse = server.registerUser(jsonRequest);
        RegisterUserDtoResponse response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getError());
        assertEquals(before, DataBase.getDatabase().countRegisteredUsers());

        before = DataBase.getDatabase().countRegisteredUsers();
        request = new RegisterUserDtoRequest(
                "", "Murashkin", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getError());
        assertEquals(before, DataBase.getDatabase().countRegisteredUsers());

        before = DataBase.getDatabase().countRegisteredUsers();
        request = new RegisterUserDtoRequest(
                "Sergei", "      ", "himik@mail.ru", "123456789");
        jsonRequest = gson.toJson(request);
        jsonResponse = server.registerUser(jsonRequest);
        response = gson.fromJson(jsonResponse, RegisterUserDtoResponse.class);
        assertEquals("error", response.getError());
        assertEquals(before, DataBase.getDatabase().countRegisteredUsers());

    }

}
