package com.asset.todo.service;

import com.asset.todo.model.TodoItem;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TodoItemService {

    TodoItem save(TodoItem item);

    TodoItem getByIdAndTodoUserUsername(Long id);

    Page<TodoItem> getAllByTodoUserUsername(int page, int size);

    void delete(Long id);

    TodoItem update(Long id, TodoItem item) throws ChangeSetPersister.NotFoundException;

    TodoItem updateDone(Long id) throws ChangeSetPersister.NotFoundException;

    Page<TodoItem> getAllByDone(Boolean done, int page, int size);

    TodoItem getItem(Long id);
}
