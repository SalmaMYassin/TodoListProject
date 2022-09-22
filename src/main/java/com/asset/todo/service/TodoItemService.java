package com.asset.todo.service;

import com.asset.todo.model.TodoItem;
import com.asset.todo.model.TodoUser;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TodoItemService {

    TodoItem save(TodoItem item);

    Optional<TodoItem> get(Long id);

    Page<TodoItem> getAll(int page, int size);

    void delete(Long id);

    TodoItem update(Long id, TodoItem item) throws ChangeSetPersister.NotFoundException;

    TodoItem markAsDone(Long id) throws ChangeSetPersister.NotFoundException;

    Page<TodoItem> getAllByDone(Boolean done, int page, int size);
}
