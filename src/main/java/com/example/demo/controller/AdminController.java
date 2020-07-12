package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
 
//import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.demo.model.Admin;
import com.example.demo.repo.AdminMongoRepository;
 
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AdminController {
 
  @Autowired
  AdminMongoRepository adminRepository;
 
  @GetMapping("/admins")
  public List<Admin> getAllAdmins() {
    System.out.println("Get all Admins...");
 
    return adminRepository.findAll();
  }
 
  @PostMapping("/admins/assign")
  public Admin createAdmin( @RequestBody Admin admin) {
    System.out.println("Add Admin: " + admin.getName() + "...");
 
    return adminRepository.save(admin);
  }
 
  @GetMapping("/admins/{id}")
  public ResponseEntity<Admin> getAdmin(@PathVariable("id") String id) {
    System.out.println("Get Book by id...");
 
    Optional<Admin> adminData = adminRepository.findById(id);
    if (adminData.isPresent()) {
      return new ResponseEntity<>(adminData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
  @PutMapping("/admins/{id}")
  public ResponseEntity<Admin> updateAdmin(@PathVariable("id") String id, @RequestBody Admin admin) {
    System.out.println("Update Admin with ID = " + id + "...");
 
    Optional<Admin> adminData = adminRepository.findById(id);
    if (adminData.isPresent()) {
      Admin savedAdmin = adminData.get();
      savedAdmin.setName(admin.getName());
      savedAdmin.setEmail(admin.getEmail());
      savedAdmin.setRole(admin.getRole());
      savedAdmin.setUsername(admin.getUsername());
      savedAdmin.setPassword(admin.getPassword());
 
      Admin updatedAdmin = adminRepository.save(savedAdmin);
      return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
 
  @DeleteMapping("/admins/{id}")
  public ResponseEntity<String> deleteAdmin(@PathVariable("id") String id) {
    System.out.println("Delete Admin with ID = " + id + "...");
 
    try {
    adminRepository.deleteById(id);
    } catch (Exception e) {
      return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
    }
 
    return new ResponseEntity<>("Admin has been deleted!", HttpStatus.OK);
  }
 
  @DeleteMapping("/admins/delete")
  public ResponseEntity<String> deleteAllAdmins() {
    System.out.println("Delete All Books...");
 
    try {
    adminRepository.deleteAll();
    } catch (Exception e) {
      return new ResponseEntity<>("Fail to delete!", HttpStatus.EXPECTATION_FAILED);
    }
 
    return new ResponseEntity<>("All Books have been deleted!", HttpStatus.OK);
  }
}
