package com.asset.todo.service;

import com.asset.todo.model.User;

import java.util.List;

public interface UserService {
    User save(User user);

    User get(String username);

    List<User> getAll();
}
