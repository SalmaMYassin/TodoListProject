package com.asset.todo;

import com.asset.todo.domain.TodoUser;
import com.asset.todo.service.TodoUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(TodoUserService todoUserService){
		return args -> {
			todoUserService.saveTodoUser(new TodoUser(null, "Salma Yassin", "salma","salma"));
			todoUserService.saveTodoUser(new TodoUser(null, "Sara Yassin", "sara","sara"));
			todoUserService.saveTodoUser(new TodoUser(null, "Sohila Yassin", "sohila","sohila"));
			todoUserService.saveTodoUser(new TodoUser(null, "Mohamed Wael", "mohamed","mohamed"));
		};
	}

}
