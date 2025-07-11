package com.example.lab9.controller;

import com.example.lab9.model.Item;
import com.example.lab9.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@CrossOrigin
public class ItemController {

    private final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Item addItem(@RequestBody Item item) {
        item.setPurchased(false);
        return repository.save(item);
    }

    @GetMapping
    public List<Item> getAll() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}/toggle")
    public Item togglePurchased(@PathVariable Long id) {
        Item item = repository.findById(id).orElseThrow();
        item.setPurchased(!item.isPurchased());
        return repository.save(item);
    }
}
