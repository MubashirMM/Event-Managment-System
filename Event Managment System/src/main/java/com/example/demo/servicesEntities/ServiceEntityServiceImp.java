package com.example.demo.servicesEntities;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ServiceEntityServiceImp implements ServiceEntityService {

    @Autowired
    private ServiceEntityRepository repository;

    @Override
    public List<ServiceEntity> getAllServices() {
        return repository.findAll();
    }

    @Override
    public ServiceEntity getServiceById(int id) {
        Optional<ServiceEntity> service = repository.findById(id);
        return service.orElse(null);
    }

    @Override
    public ServiceEntity addService(ServiceEntity serviceEntity) {
        return repository.save(serviceEntity);
    }

    @Override
    public ServiceEntity updateService(int id, ServiceEntity serviceEntity) {
        Optional<ServiceEntity> existingService = repository.findById(id);
        if (existingService.isPresent()) {
            ServiceEntity updatedService = existingService.get();
            updatedService.setName(serviceEntity.getName());
            updatedService.setDescription(serviceEntity.getDescription());
            updatedService.setPrice(serviceEntity.getPrice());
            return repository.save(updatedService);
        }
        return null;
    } 

    @Override
    public void deleteService(int id) {
        repository.deleteById(id);
    }
}
