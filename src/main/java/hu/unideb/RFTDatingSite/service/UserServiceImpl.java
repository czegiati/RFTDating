package hu.unideb.RFTDatingSite.service;

import hu.unideb.RFTDatingSite.Model.User;
import hu.unideb.RFTDatingSite.exception.ResourceNotFoundException;
import hu.unideb.RFTDatingSite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
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
            userUpdate.setPassword(user.getPassword());
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
}
