package com.example.demo.user;

import java.util.List;

public interface AdminService {
    Admin addAdmin(Admin admin);
    List<Admin> getAllAdmins();
    Admin updateAdmin(Admin admin);
    void deleteAdmin(int id);
    Admin findByEmailAndPassword(String email, String password);
}
