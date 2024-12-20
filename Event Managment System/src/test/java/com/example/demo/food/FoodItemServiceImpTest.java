package com.example.demo.food;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FoodItemServiceImpTest {

    @Mock
    private FoodItemRepository foodItemRepository;

    @InjectMocks
    private FoodItemServiceImp foodItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFoodItems() {
        // Arrange
        FoodItem item1 = new FoodItem();
        item1.setFoodId(1);
        item1.setName("Pizza");
        item1.setPrice(9.99);
        item1.setDescription("Cheese Pizza");

        FoodItem item2 = new FoodItem();
        item2.setFoodId(2);
        item2.setName("Burger");
        item2.setPrice(5.99);
        item2.setDescription("Chicken Burger");

        when(foodItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        // Act
        List<FoodItem> items = foodItemService.getAllFoodItems();

        // Assert
        assertEquals(2, items.size());
        verify(foodItemRepository, times(1)).findAll();
    }

    @Test
    void testGetFoodItemById() {
        // Arrange
        FoodItem item = new FoodItem();
        item.setFoodId(1);
        item.setName("Pizza");
        item.setPrice(9.99);
        item.setDescription("Cheese Pizza");

        when(foodItemRepository.findById(1)).thenReturn(Optional.of(item));

        // Act
        FoodItem result = foodItemService.getFoodItemById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(foodItemRepository, times(1)).findById(1);
    }

    @Test
    void testAddFoodItem() {
        // Arrange
        FoodItem item = new FoodItem();
        item.setName("Pizza");
        item.setPrice(9.99);
        item.setDescription("Cheese Pizza");

        when(foodItemRepository.save(item)).thenReturn(item);

        // Act
        FoodItem result = foodItemService.addFoodItem(item);

        // Assert
        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(foodItemRepository, times(1)).save(item);
    }

    @Test
    void testUpdateFoodItem() {
        // Arrange
        FoodItem existingItem = new FoodItem();
        existingItem.setFoodId(1);
        existingItem.setName("Pizza");
        existingItem.setPrice(9.99);
        existingItem.setDescription("Cheese Pizza");

        FoodItem updatedItem = new FoodItem();
        updatedItem.setName("Veg Pizza");
        updatedItem.setPrice(10.99);
        updatedItem.setDescription("Vegetable Pizza");

        when(foodItemRepository.findById(1)).thenReturn(Optional.of(existingItem));
        when(foodItemRepository.save(existingItem)).thenReturn(existingItem);

        // Act
        FoodItem result = foodItemService.updateFoodItem(1, updatedItem);

        // Assert
        assertNotNull(result);
        assertEquals("Veg Pizza", result.getName());
        assertEquals(10.99, result.getPrice());
        verify(foodItemRepository, times(1)).findById(1);
        verify(foodItemRepository, times(1)).save(existingItem);
    }

    @Test
    void testDeleteFoodItem() {
        // Arrange
        doNothing().when(foodItemRepository).deleteById(1);

        // Act
        foodItemService.deleteFoodItem(1);

        // Assert
        verify(foodItemRepository, times(1)).deleteById(1);
    }
}
