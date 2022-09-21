package com.asset.todo.service;

import com.asset.todo.model.TodoItem;
import com.asset.todo.model.TodoUser;
import com.asset.todo.repository.TodoItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoItemServiceImpl implements TodoItemService{
    private final TodoItemRepository todoItemRepository;


    @Override
    public TodoItem saveTodoItem(TodoItem item) {
        log.info("saving new todo item: {}", item.getTitle());
        return todoItemRepository.save(item);
    }

    @Override
    public Optional<TodoItem> getTodoItem(Long id) {
        log.info("getting todo item with id: {}", id);
        return todoItemRepository.findById(id);
    }

    @Override
    public List<TodoItem> getTodoItems() {
        log.info("fetching all items...");
        return todoItemRepository.findAll();
    }
}
