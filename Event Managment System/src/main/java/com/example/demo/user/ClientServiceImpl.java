package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client); 
    }
 
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll(); 
    }

    @Override
    public Client updateClient(Client client) {
        if (clientRepository.existsById(client.getId())) {
            return clientRepository.save(client);
        } else {
            throw new RuntimeException("Client with ID " + client.getId() + " does not exist.");
        }
    }
  
    @Override
    public void deleteClient(int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Client with ID " + id + " does not exist.");
        }
    }
    
    public Client findByEmailAndPassword(String email, String password) {
        return clientRepository.findByEmailAndPassword(email, password);
    }

}
