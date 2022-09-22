package com.asset.todo.controller;

import com.asset.todo.model.TodoItem;
import com.asset.todo.model.TodoUser;
import com.asset.todo.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ResponseEntity<List<TodoItem>> getAll() {
        return ResponseEntity.ok().body(todoItemService
                .getAllByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<TodoItem> get(@PathVariable Long id) {
        Optional<TodoItem> todoItem = todoItemService.get(id);
        return todoItem.map(item -> ResponseEntity.ok().body(item)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/item/save")
    public ResponseEntity<TodoItem> save(@RequestBody TodoItem todoItem) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/save").toUriString());
        return ResponseEntity.created(uri).body(todoItemService.save(todoItem));
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<TodoItem> delete(@PathVariable Long id) {
        todoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/item/update/{id}")
    public ResponseEntity<TodoItem> update(@PathVariable Long id, @RequestBody TodoItem todoItem) throws ChangeSetPersister.NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/update").toUriString());
        return ResponseEntity.created(uri).body(todoItemService.update(id, todoItem));
    }

    @PutMapping("/item/done/{id}")
    public ResponseEntity<TodoItem> markAsDone(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/done").toUriString());
        return ResponseEntity.created(uri).body(todoItemService.markAsDone(id));
    }
}
