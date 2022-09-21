package com.asset.todo.service;

import com.asset.todo.model.TodoUser;
import com.asset.todo.repository.TodoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public TodoUser save(TodoUser user) {
        log.info("saving new user: {}", user.getUsername());
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
