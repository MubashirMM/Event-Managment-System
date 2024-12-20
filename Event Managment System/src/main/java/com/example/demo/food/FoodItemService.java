package com.example.demo.food;

import java.util.List;

public interface FoodItemService {
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItemById(int id);
    FoodItem addFoodItem(FoodItem foodItem);
    FoodItem updateFoodItem(int id, FoodItem foodItem);
    void deleteFoodItem(int id);
}
