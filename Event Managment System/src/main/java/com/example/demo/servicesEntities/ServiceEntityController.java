package com.example.demo.servicesEntities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List; 

@RestController
@RequestMapping("/services")
public class ServiceEntityController { 

    @Autowired
    private ServiceEntityService serviceEntityService;

    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAllServices() {
        List<ServiceEntity> services = serviceEntityService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable int id) {
        ServiceEntity service = serviceEntityService.getServiceById(id);
        if (service != null) {
            return ResponseEntity.ok(service);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ServiceEntity> addService(@RequestBody ServiceEntity serviceEntity) {
        ServiceEntity createdService = serviceEntityService.addService(serviceEntity);
        return ResponseEntity.ok(createdService);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceEntity> updateService(@PathVariable int id, @RequestBody ServiceEntity serviceEntity) {
        ServiceEntity updatedService = serviceEntityService.updateService(id, serviceEntity);
        if (updatedService != null) {
            return ResponseEntity.ok(updatedService);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable int id) {
        serviceEntityService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
