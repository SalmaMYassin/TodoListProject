package com.asset.todo.repository;

import com.asset.todo.model.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    Page<TodoItem> findAllByTodoUserUsername(String username, Pageable pageable);

    TodoItem findByIdAndTodoUserUsername(Long id, String username);

    Page<TodoItem> findAllByDoneAndTodoUserUsername(Boolean done, String username, Pageable pageable);
}
