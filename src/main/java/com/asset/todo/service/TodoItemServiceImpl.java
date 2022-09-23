package com.asset.todo.service;

import com.asset.todo.model.TodoItem;
import com.asset.todo.model.TodoUser;
import com.asset.todo.repository.TodoItemRepository;
import com.asset.todo.repository.TodoUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoItemServiceImpl implements TodoItemService {
    private final TodoItemRepository todoItemRepository;
    private final TodoUserRepository todoUserRepository;


    @Override
    public TodoItem save(TodoItem item) {
        log.info("saving new todo item: {}", item.getTitle());
        if (item.getTodoUser() == null) {
            TodoUser user = todoUserRepository.findByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
            item.setTodoUser(user);
        }
        return todoItemRepository.save(item);
    }

    @Override
    public TodoItem getByIdAndTodoUserUsername(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("getting todo item with id: {}", id);
        return todoItemRepository.findByIdAndTodoUserUsername(id, username);
    }

    @Override
    public Page<TodoItem> getAllByTodoUserUsername(int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        log.info("fetching all items for user {}: ...", username);
        return todoItemRepository.findAllByTodoUserUsername(username, PageRequest.of(page, size));
    }

    @Override
    public void delete(Long id) {
        TodoItem item = getItem(id);
        log.info("deleting item: {}", item.getTitle());
        todoItemRepository.deleteById(item.getId());
    }

    @Override
    public TodoItem update(Long id, TodoItem updatedItem) throws ChangeSetPersister.NotFoundException {
        TodoItem item = getItem(id);

        if (updatedItem.getTitle() != null) {
            log.info("updating title to: {}", updatedItem.getTitle());
            item.setTitle(updatedItem.getTitle());
        }
        if (updatedItem.getDescription() != null) {
            log.info("updating description to: {}", updatedItem.getDescription());
            item.setDescription(updatedItem.getDescription());
        }

        return todoItemRepository.save(item);
    }

    @Override
    public TodoItem updateDone(Long id) throws ChangeSetPersister.NotFoundException {
        TodoItem item = getItem(id);
        item.setDone(!item.getDone());
        log.info("updating done from {} to {}", item.getDone(), !item.getDone());
        return todoItemRepository.save(item);
    }

    @Override
    public Page<TodoItem> getAllByDone(Boolean done, int page, int size) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return todoItemRepository.findAllByDoneAndTodoUserUsername(done, username, PageRequest.of(page, size));
    }

    @Override
    public TodoItem getItem(Long id) {
        TodoItem item = getByIdAndTodoUserUsername(id);
        if (item == null) {
            log.error("Item not found");
            throw new UsernameNotFoundException("Item: " + id + " is not found");
        }
        return item;
    }

}
