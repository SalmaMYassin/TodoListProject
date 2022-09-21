package com.asset.todo.service;

import com.asset.todo.model.TodoItem;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {

    TodoItem save(TodoItem item);

    Optional<TodoItem> get(Long id);

    List<TodoItem> getAll();

    void delete(Long id);

    TodoItem update(Long id, TodoItem item) throws ChangeSetPersister.NotFoundException;

    TodoItem markAsDone(Long id) throws ChangeSetPersister.NotFoundException;
}
