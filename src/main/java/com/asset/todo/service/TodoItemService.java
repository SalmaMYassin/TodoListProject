package com.asset.todo.service;

import com.asset.todo.model.TodoItem;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {

    TodoItem saveTodoItem(TodoItem  item);

    Optional<TodoItem> getTodoItem(Long id);

    List<TodoItem> getTodoItems();
}
