package com.milind.april28.assignment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.milind.april28.assignment.exception.RecordNotFoundException;
import com.milind.april28.assignment.model.Tour;
import com.milind.april28.assignment.service.TourService;

@RestController
public class TourController
{
	@Autowired
	TourService service;

	@GetMapping("/tours")
	public List<Tour> getAllTours()
	{
		return service.getAllTours();
	}

	@GetMapping("/tours/{id}")
	public ResponseEntity<Tour> getTour(@PathVariable String id)
	{
		return new ResponseEntity<Tour>(
				service.findTour(id)
						.orElseThrow(() -> new RecordNotFoundException("Tour id '" + id + "' does no exist")),
				HttpStatus.OK
		);
	}

	@PostMapping("/tours")
	public ResponseEntity<Tour> addTour(@Valid @RequestBody Tour tour)
	{
		return new ResponseEntity<Tour>(service.add(tour), HttpStatus.OK);
	}

	@PutMapping("/tours/{id}")
	public ResponseEntity<Tour> updateTour(@PathVariable String id, @Valid @RequestBody Tour tour)
	{
		tour.setId(id);
		return new ResponseEntity<Tour>(service.update(tour), HttpStatus.OK);
	}

	@DeleteMapping("/tours/{id}")
	public void deleteTour(@PathVariable String id)
	{
		service.delete(id);
	}
}
