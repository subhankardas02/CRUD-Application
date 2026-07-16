package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public Optional<User> getUser(long id) {
        return userRepository.findById(id);
    }
    public boolean updateUser(Long id, User user) {

            return userRepository.findById(id)
                    .map(existingUser -> {
                        existingUser.setFirstName(user.getFirstName());
                        existingUser.setLastName(user.getLastName());
                        userRepository.save(existingUser);
                        return true;
                    })
                    .orElse(false);
    }
}
