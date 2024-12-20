package com.example.demo.servicesEntities;

import java.util.List;

public interface ServiceEntityService {
    List<ServiceEntity> getAllServices();
    ServiceEntity getServiceById(int id);
    ServiceEntity addService(ServiceEntity serviceEntity);
    ServiceEntity updateService(int id, ServiceEntity serviceEntity);
    void deleteService(int id);
}
