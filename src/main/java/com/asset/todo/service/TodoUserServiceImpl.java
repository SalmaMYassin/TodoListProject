package com.asset.todo.service;

import com.asset.todo.model.TodoUser;
import com.asset.todo.repository.TodoUserRepository;
import com.asset.todo.security.JwtUtilities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoUserServiceImpl implements TodoUserService, UserDetailsService {
    private final TodoUserRepository todoUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TodoUser todoUser = todoUserRepository.findByUsername(username);
        if (todoUser == null) {
            log.error("User not found in the DB");
            throw new UsernameNotFoundException("User: " + username + " is not found");
        } else{
            return new User(todoUser.getUsername(), todoUser.getPassword(), JwtUtilities.getAuthorities());
        }
    }

    @Override
    public TodoUser save(TodoUser user) {
        log.info("saving new user: {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return todoUserRepository.save(user);
    }

    @Override
    public TodoUser get(String username) {
        log.info("getting user by username: {}", username);
        return todoUserRepository.findByUsername(username);
    }

    @Override
    public List<TodoUser> getAll() {
        log.info("fetching all users...");
        return todoUserRepository.findAll();
    }
}
