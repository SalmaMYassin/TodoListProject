package com.asset.todo;

import com.asset.todo.model.TodoItem;
import com.asset.todo.model.TodoUser;
import com.asset.todo.service.TodoItemService;
import com.asset.todo.service.TodoUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class TodoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    CommandLineRunner run(TodoUserService todoUserService, TodoItemService todoItemService) {
        return args -> {

            TodoUser todoUser = new TodoUser(null, "Salma Yassin", "salma", "salma");
            todoUserService.saveTodoUser(todoUser);

            todoItemService.saveTodoItem(new TodoItem(null, "Backend Assessment", "A Todo List", false, todoUser));
            todoItemService.saveTodoItem(new TodoItem(null, "Get Milk", "", false, todoUser));
            todoItemService.saveTodoItem(new TodoItem(null, "Make Dinner", "Roast Beef", false, todoUser));
            todoItemService.saveTodoItem(new TodoItem(null, "Have Fun", "foo", false, todoUser));
        };
    }

}
