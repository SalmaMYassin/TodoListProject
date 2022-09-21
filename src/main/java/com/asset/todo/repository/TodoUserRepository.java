package com.asset.todo.repository;

import com.asset.todo.domain.TodoUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoUserRepository extends JpaRepository<TodoUser, Long> {
    TodoUser findByUsername(String username);
}