package com.asset.todo.repository;

import com.asset.todo.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findAllByTodoUserUsername(String username);

}
