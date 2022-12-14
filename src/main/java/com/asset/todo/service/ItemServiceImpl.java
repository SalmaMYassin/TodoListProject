package com.asset.todo.service;

import com.asset.todo.model.Item;
import com.asset.todo.model.User;
import com.asset.todo.repository.ItemRepository;
import com.asset.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;


    @Override
    public Item save(Item item) {
        log.info("saving new todo item: {}", item.getTitle());
        if (item.getUser() == null) {
            User user = userRepository.findByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            item.setUser(user);
        }
        return itemRepository.save(item);
    }

    @Override
    public Item getById(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("getting todo item with id: {}", id);
        Item item = itemRepository.findByIdAndUserUsername(id, username);
        if (item == null) {
            log.error("Item not found");
            throw new ResponseStatusException(NOT_FOUND, "Item with id: " + id + " is not found");
        }
        return item;
    }

    @Override
    public Page<Item> getAll(int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("fetching all items for user {}: ...", username);
        return itemRepository.findAllByUserUsername(username, PageRequest.of(page, size));
    }

    @Override
    public void delete(Long id) {
        Item item = getById(id);
        log.info("deleting item: {}", item.getTitle());
        itemRepository.deleteById(item.getId());
    }

    @Override
    public void bulkDelete(List<Long> ids) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("deleting items: {}",ids.stream().unordered());
        itemRepository.deleteAllByIdAndUserUsername(ids, username);
    }

    @Override
    public Item update(Long id, Item updatedItem) {
        Item item = getById(id);

        if (updatedItem.getTitle() != null) {
            log.info("updating title to: {}", updatedItem.getTitle());
            item.setTitle(updatedItem.getTitle());
        }
        if (updatedItem.getDescription() != null) {
            log.info("updating description to: {}", updatedItem.getDescription());
            item.setDescription(updatedItem.getDescription());
        }

        return itemRepository.save(item);
    }

    @Override
    public Item updateDone(Long id) {
        Item item = getById(id);
        item.setDone(!item.getDone());
        log.info("updating done from {} to {}", item.getDone(), !item.getDone());
        return itemRepository.save(item);
    }

    @Override
    public Page<Item> getAllByDone(Boolean done, int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return itemRepository.findAllByDoneAndUserUsername(done, username, PageRequest.of(page, size));
    }

}
