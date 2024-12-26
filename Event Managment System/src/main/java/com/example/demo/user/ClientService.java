package com.example.demo.user;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);
    List<Client> getAllClients();
    Client updateClient(Client client);
    void deleteClient(int id);
    Client findByEmailAndPassword(String email, String password);

}
