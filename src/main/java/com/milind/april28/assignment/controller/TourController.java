package com.milind.april28.assignment.controller;

import java.util.List;
import java.util.NoSuchElementException;

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
	public ResponseEntity<Object> getTour(@PathVariable String id)
	{
		try
		{
			return new ResponseEntity<Object>(service.findTour(id).orElseThrow(), HttpStatus.OK);
		}
		catch (NoSuchElementException e)
		{
			return new ResponseEntity<Object>("id: " + id + " not found", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/tours")
	public ResponseEntity<Tour> addTour(@Valid @RequestBody Tour tour)
	{
		Tour savedTour = service.add(tour);

		return new ResponseEntity<Tour>(savedTour, HttpStatus.OK);
	}

	@PutMapping("/tours/{id}")
	public ResponseEntity<Object> updateTour(@PathVariable String id, @Valid @RequestBody Tour tour)
	{
		try
		{
			tour.setId(id);
			return new ResponseEntity<Object>(service.update(tour), HttpStatus.OK);
		}
		catch (NoSuchElementException e)
		{
			return new ResponseEntity<Object>("Tour with id: " + id + " not found", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/tours/{id}")
	public ResponseEntity<Object> deleteTour(@PathVariable String id)
	{
		try
		{
			service.delete(id);
			return new ResponseEntity<Object>("Deleted tour with id: " + id, HttpStatus.OK);
		}
		catch (NoSuchElementException e)
		{
			return new ResponseEntity<Object>("id "+id+" not found", HttpStatus.BAD_REQUEST);
		}
	}
}
