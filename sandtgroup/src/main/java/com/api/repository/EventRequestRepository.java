package com.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.model.EventRequest;

public interface EventRequestRepository extends MongoRepository<EventRequest, String>{

}
