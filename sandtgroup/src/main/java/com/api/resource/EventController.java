package com.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Event;
import com.api.repository.EventRepository;

@RestController
public class EventController {

	@Autowired
	private EventRepository repository;
	
	@PostMapping("/addEvent")
	public String saveEvent(@RequestBody Event event) {
		repository.save(event);
		return "Added a new event";
	}
	
	@PostMapping("/updateEvent")
	public String saveUpdatedEvent(@RequestBody Event event) {
		repository.save(event);
		return "Update the event";
	}
	
	@GetMapping("/findAllEvents")
	public List<Event> getEvens(){
		return repository.findAll();
	}
	
	@GetMapping("/findAllEvents/{id}")
	public Optional<Event> getEvent(@PathVariable String id){
		return repository.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEvent(@PathVariable String id) {
		repository.deleteById(id);
		return "book deleted with id : "+id;
	}
}
