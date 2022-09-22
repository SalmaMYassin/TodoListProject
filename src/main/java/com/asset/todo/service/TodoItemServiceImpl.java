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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public Optional<TodoItem> get(Long id) {
        log.info("getting todo item with id: {}", id);
        return todoItemRepository.findById(id);
    }

    @Override
    public Page<TodoItem> getAll(int page, int size) {
        log.info("fetching all items...");
        return todoItemRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public void delete(Long id) {
        todoItemRepository.delete(todoItemRepository.getReferenceById(id));
    }

    @Override
    public TodoItem update(Long id, TodoItem updatedItem) throws ChangeSetPersister.NotFoundException {
        TodoItem item = todoItemRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        item.setTitle(updatedItem.getTitle());
        item.setDescription(updatedItem.getDescription());
        item.setDone(updatedItem.getDone());
        return todoItemRepository.save(item);
    }

    @Override
    public TodoItem updateDone(Long id) throws ChangeSetPersister.NotFoundException {
        TodoItem item = todoItemRepository.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        item.setDone(!item.getDone());
        return todoItemRepository.save(item);
    }

    @Override
    public Page<TodoItem> getAllByDone(Boolean done, int page, int size) {
        return todoItemRepository.findAllByDone(done, PageRequest.of(page, size));
    }

}
