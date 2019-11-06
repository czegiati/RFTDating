package hu.unideb.RFTDatingSite.Model.forms;

import hu.unideb.RFTDatingSite.Model.validation.LoginValidation;

@LoginValidation(message="Your username or password is incorrect!")
public class UserLoginForm {
    String username;
    String password;

    public UserLoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
