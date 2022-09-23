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

            TodoUser todoUser = new TodoUser(null, "Salma Yassin", "salma", "salma",null);
            TodoUser todoUser2 = new TodoUser(null, "Mohamed Wael", "wael", "wael",null);
            todoUserService.save(todoUser);
            todoUserService.save(todoUser2);
        };
    }
}
