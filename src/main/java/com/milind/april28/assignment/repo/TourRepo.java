package com.milind.april28.assignment.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.milind.april28.assignment.model.Tour;

@Repository
public interface TourRepo extends MongoRepository<Tour, String>
{

}
