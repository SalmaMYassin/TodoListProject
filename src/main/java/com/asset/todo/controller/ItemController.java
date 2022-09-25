package com.asset.todo.controller;

import com.asset.todo.model.Item;
import com.asset.todo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    //  Get Item by Id
    @GetMapping("/item/{id}")
    public ResponseEntity<Item> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(itemService.getById(id));
    }

    //  Get All Items for user
    //  If done filter is passed in the parameters filter by it
    @GetMapping("/items")
    public Page<Item> getItems(@RequestParam(name = "done") @Nullable Boolean done,
                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                     @RequestParam(name = "size", defaultValue = "4") int size) {
        if (done != null) {
            return itemService.getAllByDone(done, page, size);
        } else
            return itemService.getAll(page, size);
    }

    //  Save an Item
    @PostMapping("/item/save")
    public ResponseEntity<Item> save(@RequestBody Item item) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/save").toUriString());
        return ResponseEntity.created(uri).body(itemService.save(item));
    }

    //  Delete an item
    @DeleteMapping("/item/{id}")
    public ResponseEntity<Item> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  Bulk delete items
    @DeleteMapping("/items/")
    public ResponseEntity<Item> delete(@RequestBody List<Long> ids) {
        itemService.bulkDelete(ids);
        return ResponseEntity.noContent().build();
    }

    //  Update an item
    @PutMapping("/item/update/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item updatedItem) throws ChangeSetPersister.NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/update").toUriString());
        return ResponseEntity.created(uri).body(itemService.update(id, updatedItem));
    }

    //  Change done status
    @PutMapping("/item/done/{id}")
    public ResponseEntity<Item> updateDone(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/todo/item/done").toUriString());
        return ResponseEntity.created(uri).body(itemService.updateDone(id));
    }


}
