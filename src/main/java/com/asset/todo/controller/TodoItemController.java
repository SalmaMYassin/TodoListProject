package com.asset.todo.controller;

import com.asset.todo.model.TodoItem;
import com.asset.todo.service.TodoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;

    //  Get Item by Id
    @GetMapping("/item/{id}")
    public ResponseEntity<TodoItem> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(todoItemService.getItem(id));
    }

    //  Get All Items for user
    //  If done filter is passed in the parameters filter by it
    @GetMapping("/items")
    public Page<TodoItem> getItemsByDone(@RequestParam(name = "done") @Nullable Boolean done,
                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                         @RequestParam(name = "size", defaultValue = "4") int size) {
        if (done != null) {
            return todoItemService.getAllByDone(done, page, size);
        } else
            return todoItemService.getAllByTodoUserUsername(page, size);
    }

    //  Save an Item
    @PostMapping("/item/save")
    public ResponseEntity<TodoItem> save(@RequestBody TodoItem todoItem) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/save").toUriString());
        return ResponseEntity.created(uri).body(todoItemService.save(todoItem));
    }

    //  Delete an item
    @DeleteMapping("/item/{id}")
    public ResponseEntity<TodoItem> delete(@PathVariable Long id) {
        todoItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  Update an item
    @PutMapping("/item/update/{id}")
    public ResponseEntity<TodoItem> update(@PathVariable Long id, @RequestBody TodoItem updatedItem) throws ChangeSetPersister.NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/update").toUriString());
        return ResponseEntity.created(uri).body(todoItemService.update(id, updatedItem));
    }

    //  Change done status
    @PutMapping("/item/done/{id}")
    public ResponseEntity<TodoItem> updateDone(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/done").toUriString());
        return ResponseEntity.created(uri).body(todoItemService.updateDone(id));
    }


}
