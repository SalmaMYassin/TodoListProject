package com.asset.todo.service;

import com.asset.todo.model.TodoUser;

import java.util.List;

public interface TodoUserService {
    TodoUser save(TodoUser user);

    TodoUser get(String username);

    List<TodoUser> getAll();
}
