package com.example.demo.drinks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkItemRepository extends JpaRepository<DrinkItem, Integer> {
}
