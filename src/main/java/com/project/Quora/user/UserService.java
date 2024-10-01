package com.project.Quora.user;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User saveUserToDatabase(User newUser) {
        return this.userRepository.save(newUser);
    }

    public Optional<User> getUserById(UUID id) {
        return this.userRepository.findById(id);
    }

    public User updateUser(UUID id , User userDetails) {
//        Optional<User> user =  this.userRepository.findById(id);
//        if(user.isPresent()) {
//            User existingUser = user.get();
//            existingUser.setEmail(userDetails.getEmail());
//            existingUser.setUsername(userDetails.getUsername());
//            return this.userRepository.save(existingUser);
//        }
//        return null;

        return this.userRepository.findById(id).map(existingUser -> {
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setUsername(userDetails.getUsername());
            return existingUser;
        }).orElse(null);
    }
}
