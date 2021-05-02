package com.milind.april28.assignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milind.april28.assignment.exception.RecordFoundException;
import com.milind.april28.assignment.exception.RecordNotFoundException;
import com.milind.april28.assignment.model.Tour;
import com.milind.april28.assignment.repo.TourRepo;

@Service
public class TourService
{
	@Autowired
	TourRepo repo;

	public List<Tour> findAllTours()
	{
		return repo.findAll();
	}

	public Tour findTour(String id) throws RecordNotFoundException
	{
		return repo.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Tour id '" + id + "' does not exist"));
	}

	public Tour add(Tour t) throws RecordFoundException
	{
		Optional<Tour> tourOpt = repo.findById(t.getId());

		if (tourOpt.isPresent())
		{
			throw new RecordFoundException("tried to add when tour id '" + t.getId() + "' exists");
		}
		return repo.save(t);
	}

	public Tour update(Tour tour) throws RecordNotFoundException
	{
		repo.findById(tour.getId())
				.orElseThrow(() -> new RecordNotFoundException("Tour id '" + tour.getId() + "' does not exist"));
		return repo.save(tour);
	}

	public void delete(String id) throws RecordNotFoundException
	{
		repo.findById(id).orElseThrow(() -> new RecordNotFoundException("Tour id '" + id + "' does no exist"));
		repo.deleteById(id);
	}
}
