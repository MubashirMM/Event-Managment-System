package com.example.demo.user;

import jakarta.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Client extends UserEntity {

    private LocalDate registrationDate;

    public Client() {}

    public Client(String firstName, String lastName, String password, String contact, String email, String address, LocalDate registrationDate) {
        super(firstName, lastName, password, contact, email, address);
        this.registrationDate = registrationDate;
    }

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
 
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

   
}
