package com.milind.april28.assignment.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

@RestController
public class TourController
{
	@Autowired
	private TourService service;

	@GetMapping("/tours")
	public CollectionModel<EntityModel<Tour>> getAllTours()
	{
		List<Tour> allTours = service.findAllTours();
		List<EntityModel<Tour>> allTourEntityModel = new ArrayList<>();
		allTours.forEach(t -> allTourEntityModel.add(tourLinks(t)));

		CollectionModel<EntityModel<Tour>> collectionModel = CollectionModel.of(allTourEntityModel);
		WebMvcLinkBuilder linktoself = linkTo(methodOn(TourController.class).getAllTours());
		collectionModel.add(linktoself.withRel("all-tours"));

		return collectionModel;
	}

	@GetMapping("/tours/{id}")
	public EntityModel<Tour> getTour(@PathVariable String id)
	{
		Tour tour = service.findTour(id);
		EntityModel<Tour> resource = tourLinks(tour);

		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllTours());
		resource.add(linkTo.withRel("all-tours"));

		return resource;
	}

	@PostMapping("/tours")
	public ResponseEntity<Tour> addTour(@Valid @RequestBody Tour tour)
	{
		Tour addedTour = service.add(tour);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedTour.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/tours/{id}")
	public ResponseEntity<Tour> updateTour(@PathVariable String id, @Valid @RequestBody Tour tour)
	{
		tour.setId(id);
		service.update(tour);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/tours/{id}")
	public ResponseEntity<Tour> deleteTour(@PathVariable String id)
	{
		service.delete(id);

		return ResponseEntity.noContent().build();
	}

	private static EntityModel<Tour> tourLinks(Tour tour)
	{
		EntityModel<Tour> resource = EntityModel.of(tour);

		WebMvcLinkBuilder linkToSelf = linkTo(methodOn(TourController.class).getTour(tour.getId()));
		resource.add(linkToSelf.withRel("self"));

		WebMvcLinkBuilder linkToDelete = linkTo(methodOn(TourController.class).deleteTour(tour.getId()));
		resource.add(linkToDelete.withRel("delete-tour"));

		WebMvcLinkBuilder linkToUpdate = linkTo(methodOn(TourController.class).updateTour(tour.getId(), tour));
		resource.add(linkToUpdate.withRel("update-tour"));

		return resource;
	}
}
