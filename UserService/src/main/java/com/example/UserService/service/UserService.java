package com.example.UserService.service;

import com.example.UserService.domain.LoginDTO;
import com.example.UserService.domain.User;
import com.example.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        user.setLogged(false);
        userRepository.save(user);
        Optional<User> opt = userRepository.findById(user.getUsername());
        return opt.orElse(null);
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public Boolean userExists(String username) {
        Optional<User> opt = userRepository.findById(username);
        return opt.isPresent();
    }

    public Boolean isLoggedIn(String username) {
        Optional<User> user = userRepository.findById(username);
        if(!user.isPresent()){
            throw new IllegalArgumentException("User with username: " + username + "doesn't exist!");
        }
        return user.get().isLogged();
    }

    public String login(LoginDTO loginDto) {
        Optional<User> userOpt = userRepository.findById(loginDto.getUsername());
        if(!userOpt.isPresent()){
            throw new IllegalArgumentException("User with username: " + loginDto.getUsername() + "doesn't exist!");
        }
        User user = userOpt.get();
        if(loginDto.getPassword().equals(user.getPassword())){
            user.setLogged(true);
            userRepository.save(user);
            return "Logged in!";
        }

        throw new IllegalArgumentException("Incorrect password, please try again!");
    }

    public String getName(String username) {
        Optional<User> user = userRepository.findById(username);
        if(!user.isPresent()){
            throw new IllegalArgumentException("User with username: " + username + "doesn't exist!");
        }
        return user.get().getFirstName() + " " + user.get().getLastName();
    }
}
