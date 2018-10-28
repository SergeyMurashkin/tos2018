package net.thumbtack.school.concert.dto.request;

import com.google.gson.Gson;

public class RegisterUserDtoRequest {

    private String firstName;
    private String lastName;
    private String login;
    private String password;

    public RegisterUserDtoRequest(){
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

    public String validate() {

        if ( firstName == null || firstName.equals("")) {
            return "error: empty first name";
        }
        if ( firstName.contains(" ")) {
            return "error: space in the first name";
        }
        if (lastName == null || lastName.equals("")){
            return "error: empty last name";
        }
        if (lastName.contains(" ")){
            return "error: space in the last name";
        }
        if ( login == null || login.equals("")) {
            return "error: empty login";
        }
        if ( login.contains(" ")) {
            return "error: space in the login";
        }
        if (password == null || password.equals("")){
            return "error: empty password";
        }
        if ( password.length()<6) {
            return "error: short password";
        }
        if ( password.contains(" ") ) {
            return "error: space in the password";
        }else{
            return new Gson().toJson(this, RegisterUserDtoRequest.class);
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.trim();
    }
}
