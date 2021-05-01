package com.milind.april28.assignment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milind.april28.assignment.exception.RecordNotFoundException;
import com.milind.april28.assignment.model.Tour;
import com.milind.april28.assignment.repo.TourRepo;

@Service
public class TourService
{
	@Autowired
	TourRepo repo;

	public List<Tour> getAllTours()
	{
		return repo.findAll();
	}

	public Optional<Tour> findTour(String id) throws NoSuchElementException
	{
		return repo.findById(id);
	}

	public Tour add(Tour t)
	{
		return repo.save(t);
	}

	public Tour update(Tour tour)
	{
		repo.findById(tour.getId())
				.orElseThrow(() -> new RecordNotFoundException("Tour id '" + tour.getId() + "' does no exist"));
		return repo.save(tour);
	}

	public void delete(String id)
	{
		repo.findById(id).orElseThrow(() -> new RecordNotFoundException("Tour id '" + id + "' does no exist"));
		repo.deleteById(id);
	}
}
