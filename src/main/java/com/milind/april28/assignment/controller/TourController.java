package com.milind.april28.assignment.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.milind.april28.assignment.model.Tour;
import com.milind.april28.assignment.service.TourService;

import io.swagger.annotations.ApiOperation;

@RestController
public class TourController
{
	@Autowired
	private TourService service;

	@ApiOperation(value = "View a list of all available tours", consumes = "none", produces = "application/json")
	@GetMapping("/tours")
	public ResponseEntity<List<Tour>> getAllTours()
	{
		List<Tour> allTours = service.findAllTours();
		return new ResponseEntity<>(allTours, HttpStatus.OK);
	}

	@ApiOperation(value = "Get existing tour by id", consumes = "none", produces = "application/json")
	@GetMapping("/tours/{id}")
	public ResponseEntity<Tour> getTour(@PathVariable String id)
	{
		Tour tour = service.findTour(id);
		return new ResponseEntity<>(tour, HttpStatus.OK);
	}

	@ApiOperation(value = "Add a new tour", consumes = "application/json", produces = "none")
	@PostMapping("/tours")
	public ResponseEntity<Tour> addTour(@Valid @RequestBody Tour tour)
	{
		Tour addedTour = service.add(tour);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedTour.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@ApiOperation(value = "Update existing tour", consumes = "application/json", produces = "none")
	@PutMapping("/tours/{id}")
	public ResponseEntity<Tour> updateTour(@PathVariable String id, @Valid @RequestBody Tour tour)
	{
		tour.setId(id);
		service.update(tour);

		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Delete existing tour", consumes = "none", produces = "none")
	@DeleteMapping("/tours/{id}")
	public ResponseEntity<Tour> deleteTour(@PathVariable String id)
	{
		service.delete(id);

		return ResponseEntity.noContent().build();
	}
}
