package com.github.diogocerqueiralima.usersservice.services;

import com.github.diogocerqueiralima.usersservice.exceptions.UserAlreadyExistsException;
import com.github.diogocerqueiralima.usersservice.exceptions.UserNotFoundException;
import com.github.diogocerqueiralima.usersservice.model.User;
import com.github.diogocerqueiralima.usersservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User create(
            String firstName, String lastName, String username, String email, String password, Set<User.Role> roles
    ) {

        if (userRepository.existsByUsernameOrEmail(username, email))
            throw new UserAlreadyExistsException();

        return userRepository.save(
                new User(
                        firstName, lastName, username, email, password, roles
                )
        );
    }

}
