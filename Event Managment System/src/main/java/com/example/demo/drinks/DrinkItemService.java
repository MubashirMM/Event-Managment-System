package com.example.demo.drinks;

import java.util.List;

public interface DrinkItemService {
    List<DrinkItem> getAllDrinkItems();
    DrinkItem getDrinkItemById(int id);
    DrinkItem addDrinkItem(DrinkItem drinkItem);
    DrinkItem updateDrinkItem(int id, DrinkItem drinkItem);
    void deleteDrinkItem(int id);
}
