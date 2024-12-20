package com.example.demo.servicesEntities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ServiceEntityServiceImpTest {

    @Mock 
    private ServiceEntityRepository repository;

    @InjectMocks
    private ServiceEntityServiceImp service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllServices() {
        // Arrange
        ServiceEntity service1 = new ServiceEntity();
        service1.setId(1);
        service1.setName("Service 1");
        ServiceEntity service2 = new ServiceEntity();
        service2.setId(2);
        service2.setName("Service 2");
        when(repository.findAll()).thenReturn(Arrays.asList(service1, service2));

        // Act
        List<ServiceEntity> services = service.getAllServices();

        // Assert
        assertEquals(2, services.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testGetServiceById_Found() {
        // Arrange
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setId(1);
        serviceEntity.setName("Service 1");
        when(repository.findById(1)).thenReturn(Optional.of(serviceEntity));

        // Act
        ServiceEntity result = service.getServiceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Service 1", result.getName());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testGetServiceById_NotFound() {
        // Arrange
        when(repository.findById(1)).thenReturn(Optional.empty());

        // Act
        ServiceEntity result = service.getServiceById(1);

        // Assert
        assertNull(result);
        verify(repository, times(1)).findById(1);
    }

    @Test
    void testAddService() {
        // Arrange
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName("New Service");
        when(repository.save(serviceEntity)).thenReturn(serviceEntity);

        // Act
        ServiceEntity result = service.addService(serviceEntity);

        // Assert
        assertNotNull(result);
        assertEquals("New Service", result.getName());
        verify(repository, times(1)).save(serviceEntity);
    }

    @Test
    void testUpdateService_Found() {
        // Arrange
        ServiceEntity existingService = new ServiceEntity();
        existingService.setId(1);
        existingService.setName("Existing Service");
        ServiceEntity updatedService = new ServiceEntity();
        updatedService.setName("Updated Service");
        updatedService.setDescription("Updated Description");

        when(repository.findById(1)).thenReturn(Optional.of(existingService));
        when(repository.save(any(ServiceEntity.class))).thenReturn(existingService);

        // Act
        ServiceEntity result = service.updateService(1, updatedService);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Service", result.getName());
        assertEquals("Updated Description", result.getDescription());
        verify(repository, times(1)).findById(1);
        verify(repository, times(1)).save(existingService);
    }

    @Test
    void testUpdateService_NotFound() {
        // Arrange
        ServiceEntity updatedService = new ServiceEntity();
        when(repository.findById(1)).thenReturn(Optional.empty());

        // Act
        ServiceEntity result = service.updateService(1, updatedService);

        // Assert
        assertNull(result);
        verify(repository, times(1)).findById(1);
        verify(repository, never()).save(any(ServiceEntity.class));
    }

    @Test
    void testDeleteService() {
        // Act
        service.deleteService(1);

        // Assert
        verify(repository, times(1)).deleteById(1);
    }
}
