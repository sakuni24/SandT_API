package com.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.model.Event;

public interface EventRepository extends MongoRepository<Event, String>{

}
