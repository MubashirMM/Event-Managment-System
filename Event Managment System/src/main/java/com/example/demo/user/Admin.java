package com.example.demo.user;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Admin extends UserEntity {

    private LocalDate dateAdded;

    public Admin() {}

    public Admin(String firstName, String lastName, String password, String contact, String email, String address, LocalDate dateAdded) {
        super(firstName, lastName, password, contact, email, address);
        this.dateAdded = dateAdded;
    }

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

   
}
 