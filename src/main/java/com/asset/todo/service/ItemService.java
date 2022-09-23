package com.asset.todo.service;

import com.asset.todo.model.Item;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;

public interface ItemService {

    Item save(Item item);

    Item getById(Long id);

    Page<Item> getAll(int page, int size);

    void delete(Long id);

    Item update(Long id, Item item) throws ChangeSetPersister.NotFoundException;

    Item updateDone(Long id) throws ChangeSetPersister.NotFoundException;

    Page<Item> getAllByDone(Boolean done, int page, int size);

    Item getItem(Long id);
}
