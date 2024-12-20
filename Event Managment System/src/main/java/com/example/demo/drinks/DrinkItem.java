package com.example.demo.drinks;

	import jakarta.persistence.Entity;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;

	@Entity
	public class DrinkItem {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int drinkId;
		private String name;
		private double price;
		private String description;
		
		public int getDrinkId() {
			return drinkId;
		}
		public void setDrinkId(int drinkId) {
			this.drinkId = drinkId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
		
	}

