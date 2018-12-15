package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;
import net.thumbtack.school.concert.requestException.RequestErrorCode;
import net.thumbtack.school.concert.requestException.RequestException;

public class RegisterUserDtoRequest {

    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public RegisterUserDtoRequest() {
    }

    public RegisterUserDtoRequest(String firstName, String lastName, String login, String password) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.login = login.trim();
        this.password = password.trim();
    }

    public RegisterUserDtoRequest createRegUserDto(String jsonString) {
        return new Gson().fromJson(jsonString, RegisterUserDtoRequest.class);
    }

    public void validate() throws RequestException {
        if (firstName == null || firstName.equals("")) {
            throw new RequestException(RequestErrorCode.EMPTY_FIRSTNAME_STRING);
        }
        if (firstName.contains(" ")) {
            throw new RequestException(RequestErrorCode.SPACE_FIRSTNAME_STRING);
        }
        if (lastName == null || lastName.equals("")) {
            throw new RequestException(RequestErrorCode.EMPTY_LASTNAME_STRING);
        }
        if (lastName.contains(" ")) {
            throw new RequestException(RequestErrorCode.SPACE_LASTNAME_STRING);
        }
        if (login == null || login.equals("")) {
            throw new RequestException(RequestErrorCode.EMPTY_LOGIN_STRING);
        }
        if (login.contains(" ")) {
            throw new RequestException(RequestErrorCode.SPACE_LOGIN_STRING);
        }
        if (password == null || password.equals("")) {
            throw new RequestException(RequestErrorCode.EMPTY_PASSWORD_STRING);
        }
        if (password.contains(" ")) {
            throw new RequestException(RequestErrorCode.SPACE_PASSWORD_STRING);
        }
        if (password.length() < 6) {
            throw new RequestException(RequestErrorCode.WRONG_PASSWORD_LENGTH);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


}
