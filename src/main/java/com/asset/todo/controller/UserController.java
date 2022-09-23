package com.asset.todo.controller;

import com.asset.todo.model.User;
import com.asset.todo.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //  Get all users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok().body(userService.getAll());
    }

    //  Get user
    @GetMapping("/user/{username}")
    public ResponseEntity<User> get(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.get(username));
    }

    //  Save a user
    @PostMapping("/user/register")
    public ResponseEntity<User> save(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.save(user));
    }
}
