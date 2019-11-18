package hu.unideb.RFTDatingSite.service;


import hu.unideb.RFTDatingSite.Model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void deleteUser(int id);
    boolean isUsername(String username);
    boolean isEmail(String email);

    boolean correctLogIn(String username,String password);

}
