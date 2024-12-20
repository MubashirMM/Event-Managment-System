package com.example.demo.drinks;

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

class DrinkItemServiceImpTest {

    @Mock
    private DrinkItemRepository DrinkItemRepository;

    @InjectMocks
    private DrinkItemServiceImp DrinkItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllDrinkItems() {
        // Arrange
        DrinkItem item1 = new DrinkItem();
        item1.setDrinkId(1);
        item1.setName("Pizza");
        item1.setPrice(9.99);
        item1.setDescription("Cheese Pizza");

        DrinkItem item2 = new DrinkItem();
        item2.setDrinkId(2);
        item2.setName("Burger");
        item2.setPrice(5.99);
        item2.setDescription("Chicken Burger");

        when(DrinkItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        // Act
        List<DrinkItem> items = DrinkItemService.getAllDrinkItems();

        // Assert
        assertEquals(2, items.size());
        verify(DrinkItemRepository, times(1)).findAll(); 
    }

    @Test
    void testGetDrinkItemById() { 
        // Arrange
        DrinkItem item = new DrinkItem();
        item.setDrinkId(1); 
        item.setName("Pizza");
        item.setPrice(9.99);
        item.setDescription("Cheese Pizza");

        when(DrinkItemRepository.findById(1)).thenReturn(Optional.of(item));

        // Act
        DrinkItem result = DrinkItemService.getDrinkItemById(1);

        // Assert
        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(DrinkItemRepository, times(1)).findById(1);
    }

    @Test
    void testAddDrinkItem() {
        // Arrange
        DrinkItem item = new DrinkItem();
        item.setName("Pizza");
        item.setPrice(9.99);
        item.setDescription("Cheese Pizza");

        when(DrinkItemRepository.save(item)).thenReturn(item);

        // Act
        DrinkItem result = DrinkItemService.addDrinkItem(item);

        // Assert
        assertNotNull(result);
        assertEquals("Pizza", result.getName());
        verify(DrinkItemRepository, times(1)).save(item);
    }

    @Test
    void testUpdateDrinkItem() {
        // Arrange
        DrinkItem existingItem = new DrinkItem();
        existingItem.setDrinkId(1);
        existingItem.setName("Pizza");
        existingItem.setPrice(9.99);
        existingItem.setDescription("Cheese Pizza");

        DrinkItem updatedItem = new DrinkItem();
        updatedItem.setName("Veg Pizza");
        updatedItem.setPrice(10.99);
        updatedItem.setDescription("Vegetable Pizza");

        when(DrinkItemRepository.findById(1)).thenReturn(Optional.of(existingItem));
        when(DrinkItemRepository.save(existingItem)).thenReturn(existingItem);

        // Act
        DrinkItem result = DrinkItemService.updateDrinkItem(1, updatedItem);

        // Assert
        assertNotNull(result);
        assertEquals("Veg Pizza", result.getName());
        assertEquals(10.99, result.getPrice());
        verify(DrinkItemRepository, times(1)).findById(1);
        verify(DrinkItemRepository, times(1)).save(existingItem);
    }

    @Test
    void testDeleteDrinkItem() {
        // Arrange
        doNothing().when(DrinkItemRepository).deleteById(1);

        // Act
        DrinkItemService.deleteDrinkItem(1);

        // Assert
        verify(DrinkItemRepository, times(1)).deleteById(1);
    }
}

