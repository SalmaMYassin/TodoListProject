package com.asset.todo.service;

import com.asset.todo.domain.TodoUser;
import com.asset.todo.repository.TodoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoUserServiceImpl implements TodoUserService {
    private final TodoUserRepository todoUserRepository;

    @Override
    public TodoUser saveTodoUser(TodoUser user) {
        log.info("saving new user: {}", user.getUsername());
        return todoUserRepository.save(user);
    }

    @Override
    public TodoUser getTodoUser(String username) {
        log.info("getting user by username: {}", username);
        return todoUserRepository.findByUsername(username);
    }

    @Override
    public List<TodoUser> getTodoUsers() {
        log.info("fetching all users...");
        return todoUserRepository.findAll();
    }


}
