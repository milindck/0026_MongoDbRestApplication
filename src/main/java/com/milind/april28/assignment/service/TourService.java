package com.milind.april28.assignment.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		repo.save(t);
		return t;
	}

	public Tour update(Tour tour) throws NoSuchElementException
	{
		repo.findById(tour.getId()).orElseThrow();
		return repo.save(tour);
	}

	public void delete(String id) throws NoSuchElementException
	{
		repo.findById(id).orElseThrow();
		repo.deleteById(id);
	}
}
