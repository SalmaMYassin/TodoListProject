package com.asset.todo.service;

import com.asset.todo.model.User;
import com.asset.todo.repository.UserRepository;
import com.asset.todo.security.JwtUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;


import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User: " + username + " is not found");
        } else {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), JwtUtilities.getAuthorities());
        }
    }

    @Override
    public User save(User user) {
        log.info("saving new user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User get(String username) {
        User user = userRepository.findByUsername(username);
        log.info("getting user by username: {}", username);
        if (user == null) {
            log.error("User not found in the DB");
            throw new ResponseStatusException(NOT_FOUND, "User: " + username + " is not found");
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        log.info("fetching all users...");
        return userRepository.findAll();
    }
}
