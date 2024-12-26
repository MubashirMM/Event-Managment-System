package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query("SELECT c FROM Client c WHERE c.email = :email AND c.password = :password")
	Client findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
