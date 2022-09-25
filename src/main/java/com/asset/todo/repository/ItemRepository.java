package com.asset.todo.repository;

import com.asset.todo.model.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findAllByUserUsername(String username, Pageable pageable);

    Item findByIdAndUserUsername(Long id, String username);

    Page<Item> findAllByDoneAndUserUsername(Boolean done, String username, Pageable pageable);
    void deleteAllByIdAndUserUsername(List<Long> ids, String username);
}
