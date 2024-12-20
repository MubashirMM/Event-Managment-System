package com.example.demo.drinks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drinks")
public class DrinkItemController {

    @Autowired
    private DrinkItemService drinkItemService;

    @GetMapping
    public List<DrinkItem> getAllDrinkItems() {
        return drinkItemService.getAllDrinkItems();
    }

    @GetMapping("/{id}")
    public DrinkItem getDrinkItemById(@PathVariable int id) {
        return drinkItemService.getDrinkItemById(id);
    }

    @PostMapping
    public DrinkItem addDrinkItem(@RequestBody DrinkItem drinkItem) {
        return drinkItemService.addDrinkItem(drinkItem);
    }

    @PutMapping("/{id}")
    public DrinkItem updateDrinkItem(@PathVariable int id, @RequestBody DrinkItem drinkItem) {
        return drinkItemService.updateDrinkItem(id, drinkItem);
    }

    @DeleteMapping("/{id}")
    public void deleteDrinkItem(@PathVariable int id) {
        drinkItemService.deleteDrinkItem(id);
    }
}
