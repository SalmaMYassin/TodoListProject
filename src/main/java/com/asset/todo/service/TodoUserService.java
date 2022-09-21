package com.asset.todo.service;

import com.asset.todo.model.TodoUser;

import java.util.List;

public interface TodoUserService {
    TodoUser saveTodoUser(TodoUser user);

    TodoUser getTodoUser(String username);

    List<TodoUser> getTodoUsers();
}
