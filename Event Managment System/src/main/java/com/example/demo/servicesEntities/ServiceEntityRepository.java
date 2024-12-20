package com.example.demo.servicesEntities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Integer> {
    // Additional custom query methods can be added here if needed
}
