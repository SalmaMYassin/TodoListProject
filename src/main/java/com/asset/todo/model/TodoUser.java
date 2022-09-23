package com.asset.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoUser {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @NotEmpty
    @Column(unique = true)
    private String username;
    @NotBlank
    @JsonIgnore
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "todoUser", cascade = CascadeType.ALL)
    List<TodoItem> todoItems = new ArrayList<>();
}
