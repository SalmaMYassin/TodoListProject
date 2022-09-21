package com.asset.todo.controller;

import com.asset.todo.model.TodoItem;
import com.asset.todo.model.TodoUser;
import com.asset.todo.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    @GetMapping("/items")
    public ResponseEntity<List<TodoItem>> getTodoItems() {
        return ResponseEntity.ok().body(todoItemService.getTodoItems());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<TodoItem> getTodoUser(@PathVariable Long id) {
        Optional<TodoItem> todoItem = todoItemService.getTodoItem(id);
        if(todoItem.isPresent())
            return ResponseEntity.ok().body(todoItem.get());
        else
            return (ResponseEntity<TodoItem>) ResponseEntity.notFound();
    }



}
