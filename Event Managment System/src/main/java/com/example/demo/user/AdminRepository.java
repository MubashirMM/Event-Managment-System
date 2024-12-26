package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query("SELECT a FROM Admin a WHERE a.email = :email AND a.password = :password")
	Admin findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

}
