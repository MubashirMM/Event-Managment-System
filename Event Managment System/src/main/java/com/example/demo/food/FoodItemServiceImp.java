package com.example.demo.food;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodItemServiceImp implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Override
    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll(); 
    }

    @Override
    public FoodItem getFoodItemById(int id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found with id " + id));
    }

    @Override
    public FoodItem addFoodItem(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    @Override
    public FoodItem updateFoodItem(int id, FoodItem foodItem) {
        FoodItem existingFoodItem = getFoodItemById(id);
        existingFoodItem.setName(foodItem.getName());
        existingFoodItem.setPrice(foodItem.getPrice());
        existingFoodItem.setDescription(foodItem.getDescription());
        return foodItemRepository.save(existingFoodItem);
    }

    @Override
    public void deleteFoodItem(int id) {
        foodItemRepository.deleteById(id);
    }
}
