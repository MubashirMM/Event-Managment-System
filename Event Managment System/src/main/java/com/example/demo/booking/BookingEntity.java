package com.example.demo.booking;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BookingEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookingId;
	private int userId;
	private int hallId;
	private int locationId;
	private int foodId;
	private int drinkId;
	private double totalCost;
	private LocalDate eventDate;
	private int noOfPersons; 
	private LocalDate bookingDate;
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BookingEntity() {}
	
	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getHallId() {
		return hallId;
	}


	public void setHallId(int hallId) {
		this.hallId = hallId;
	}


	public int getLocationId() {
		return locationId;
	}


	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}


	public int getFoodId() {
		return foodId;
	}


	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}


	public int getDrinkId() {
		return drinkId;
	}


	public void setDrinkId(int drinkId) {
		this.drinkId = drinkId;
	}


	public double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}


	public LocalDate getEventDate() {
		return eventDate;
	}
 

	public void setEventDate(LocalDate localDate) {
		this.eventDate = localDate;
	}


	public LocalDate getBookingDate() {
		return bookingDate;
	}


	public void setBookingDate(LocalDate localDate) {
		this.bookingDate = localDate;
	} 


	public int getId() { 
		return bookingId;
	} 
	public void setId(int id) {
		bookingId = id;
	}

	public int getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

 
}
