package hu.unideb.RFTDatingSite.service;

import hu.unideb.RFTDatingSite.Model.DateFunctions;
import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.exception.ResourceNotFoundException;
import hu.unideb.RFTDatingSite.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userDb= this.userRepository.findById(user.getUser_id());

        if(userDb.isPresent()){
            User userUpdate= userDb.get();

            userUpdate.setUser_id(user.getUser_id());
            userUpdate.setUsername(user.getUsername());
            userUpdate.setBio(user.getBio());
            userUpdate.setBirthdate(user.getBirthdate());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setFull_name(user.getFull_name());
            userUpdate.setSex(user.getSex());
            userUpdate.setSexualOrientation(user.getSexualOrientation());
            userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            return userUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found; ID:"+user.getUser_id());
        }
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    @Override
    public User getUserById(int id) {
        Optional<User> userDb = this.userRepository.findById(id);
        if (userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found; ID:" + id);

        }
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userDb = this.userRepository.findById(id);
        if (userDb.isPresent()) {
            this.userRepository.delete(userDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found; ID:" + id);

        }
    }

    @Override
    public boolean isUsername(String username) {
        User u=userRepository.getUsersByUsername(username);
        if(username.equals("") ) return false;
        if(u!=null) return true;
            return false;
    }

    @Override
    public boolean isEmail(String email) {
        User u=userRepository.getUsersByEmail(email);
        System.out.println(u);
        if(email.equals("") ) return false;
        if(u!=null) return true;
        return false;
    }

    @Override
    public boolean correctLogIn(String username, String password) {
        User u=userRepository.getUsersByUsername(username);
        if(u.getPassword().equals(passwordEncoder.encode(password))) return true;
        else return false;
    }

    @Override
    public List<User> getUsersInAgeRange(int min, int max) {

        List list= null;
        try {
            list = userRepository.getUsersBornBetween(
                    new SimpleDateFormat("yyyy-MM-dd").parse(DateFunctions.getDateFromYearsAgoInString(max)),
                    new SimpleDateFormat("yyyy-MM-dd").parse(DateFunctions.getDateFromYearsAgoInString(min))).stream().collect(Collectors.toList());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Dates : ");
        for(Object u:list)
        {
            System.out.println(((User)u).toString());
        }
        return list;
    }


}
