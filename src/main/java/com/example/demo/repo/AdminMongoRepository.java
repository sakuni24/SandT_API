package com.example.demo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Admin;
 
public interface AdminMongoRepository extends MongoRepository<Admin, String> {
 
}
