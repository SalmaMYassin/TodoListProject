package com.asset.todo.controller;

import com.asset.todo.model.Item;
import com.asset.todo.model.User;
import com.asset.todo.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ItemControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ItemService itemService;

    @InjectMocks
    private ItemController itemController;

    User user = new User(null, "bob", "bob", "bob", null);
    Item item1 = new Item(null, "Kill zombies", "Survive the apocalypse", false, user);
    Item item2 = new Item(null, "Find food", "Search the woods", false, user);
    Item item3 = new Item(null, "Start a fire", "", true, user);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
    }

    @Test
    void get() throws Exception {
        when(itemService.getById(1L)).thenReturn(item1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/item/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Kill zombies")));
    }

    @Test
    void getItems() throws Exception {
        List<Item> itemList = new ArrayList<>(Arrays.asList(item1, item2, item3));
        Page<Item> page = new PageImpl<>(itemList);

        when(itemService.getAll(0,4)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/items")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(3)))
                .andExpect(jsonPath("$.content.[2].title", is("Start a fire")));
    }

    @Test
    void getItemsOnlyDone() throws Exception {
        List<Item> itemList = new ArrayList<>(Arrays.asList(item3));
        Page<Item> page = new PageImpl<>(itemList);

        when(itemService.getAllByDone(true, 0,4)).thenReturn(page);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/items")
                        .param("done","true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content.[0].title", is("Start a fire")));
    }
}