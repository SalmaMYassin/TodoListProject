package com.asset.todo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String title;
    private String description;
    private Boolean done = Boolean.FALSE;
    @NotNull
    @ManyToOne
    private User user;
}
