package com.asset.todo.controller;

import com.asset.todo.model.TodoUser;
import com.asset.todo.service.TodoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoUserController {

    private final TodoUserService todoUserService;

    @GetMapping("/users")
    public ResponseEntity<List<TodoUser>> getTodoUsers() {
        return ResponseEntity.ok().body(todoUserService.getAll());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<TodoUser> getTodoUser(@PathVariable String username) {
        return ResponseEntity.ok().body(todoUserService.get(username));
    }

    @PostMapping("/user/save")
    public ResponseEntity<TodoUser> saveTodoUsers(@RequestBody TodoUser todoUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/user/save").toUriString());
        return ResponseEntity.created(uri).body(todoUserService.save(todoUser));
    }
}
