package com.asset.todo.repository;

import com.asset.todo.model.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
//    Page<TodoItem> findAllByTodoUserUsername(int page, int size);
    Page<TodoItem> findAllByDone(Boolean done, PageRequest pageRequest);
}
