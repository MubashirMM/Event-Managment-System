package com.example.demo.drinks;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrinkItemServiceImp implements DrinkItemService {

    @Autowired
    private DrinkItemRepository drinkItemRepository;

    @Override
    public List<DrinkItem> getAllDrinkItems() {
        return drinkItemRepository.findAll();
    }

    @Override
    public DrinkItem getDrinkItemById(int id) {
        return drinkItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drink item not found with id " + id));
    }

    @Override
    public DrinkItem addDrinkItem(DrinkItem drinkItem) {
        return drinkItemRepository.save(drinkItem);
    }

    @Override
    public DrinkItem updateDrinkItem(int id, DrinkItem drinkItem) {
        DrinkItem existingDrinkItem = getDrinkItemById(id);
        existingDrinkItem.setName(drinkItem.getName());
        existingDrinkItem.setPrice(drinkItem.getPrice());
        existingDrinkItem.setDescription(drinkItem.getDescription());
        return drinkItemRepository.save(existingDrinkItem);
    }

    @Override
    public void deleteDrinkItem(int id) {
        drinkItemRepository.deleteById(id);
    }
}
