package com.asset.todo.api;

import com.asset.todo.domain.TodoUser;
import com.asset.todo.service.TodoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TodoUserResource {
    private final TodoUserService todoUserService;

    @GetMapping("/users")
    public ResponseEntity<List<TodoUser>> getTodoUsers() {
        return ResponseEntity.ok().body(todoUserService.getTodoUsers());
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<TodoUser> getTodoUser(@PathVariable String username) {
        return ResponseEntity.ok().body(todoUserService.getTodoUser(username));
    }

    @PostMapping("/user/save")
    public ResponseEntity<TodoUser> saveTodoUsers(@RequestBody TodoUser todoUser) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(todoUserService.saveTodoUser(todoUser));
    }
}
