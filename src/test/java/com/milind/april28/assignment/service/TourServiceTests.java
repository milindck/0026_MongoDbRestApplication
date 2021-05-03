package com.milind.april28.assignment.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.milind.april28.assignment.exception.RecordFoundException;
import com.milind.april28.assignment.exception.RecordNotFoundException;
import com.milind.april28.assignment.model.Tour;
import com.milind.april28.assignment.repo.TourRepo;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TourServiceTests
{
	@InjectMocks
	private TourService service;

	@Mock
	private TourRepo repo;

	@Test
	public void testAddRecordExists()
	{
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Optional<Tour> optT1 = Optional.of(tOne);
		when(repo.findById("1")).thenReturn(optT1);
		assertThatExceptionOfType(RecordFoundException.class).isThrownBy(() -> service.add(tOne));
	}

	@Test
	public void testAddRecordNotExists()
	{
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Optional<Tour> optNull = Optional.ofNullable(null);
		when(repo.findById("1")).thenReturn(optNull);
		when(repo.save(tOne)).thenReturn(tOne);
		assertEquals(tOne, service.add(tOne));
	}

	@Test
	public void testDeleteExisting()
	{
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Optional<Tour> optT1 = Optional.of(tOne);
		when(repo.findById("1")).thenReturn(optT1);
		doNothing().when(repo).deleteById("1");

		service.delete("1");
	}

	@Test
	public void testDeleteNonExisting()
	{
		when(repo.findById("1")).thenReturn(Optional.ofNullable(null));
		doNothing().when(repo).deleteById("1");
		assertThatExceptionOfType(RecordNotFoundException.class).isThrownBy(() -> service.delete("1"));
	}

	@Test
	public void testFindAll()
	{
		List<Tour> list = new ArrayList<Tour>();
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Tour tTwo = new Tour(
				"2", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);

		Tour tThree = new Tour(
				"3", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);

		list.add(tOne);
		list.add(tTwo);
		list.add(tThree);

		when(repo.findAll()).thenReturn(list);

		// test
		List<Tour> empList = service.findAllTours();

		assertEquals(3, empList.size());
		verify(repo, times(1)).findAll();
	}

	@Test
	public void testFindTour()
	{
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Tour tTwo = new Tour(
				"2", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);

		Optional<Tour> optT1 = Optional.of(tOne);
		when(repo.findById("1")).thenReturn(optT1);

		Optional<Tour> optT2 = Optional.of(tTwo);
		when(repo.findById("2")).thenReturn(optT2);

		assertEquals(optT1, repo.findById("1"));
		assertEquals(optT2, repo.findById("2"));
	}

	@Test
	public void testUpdateExisting()
	{
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Optional<Tour> optT1 = Optional.of(tOne);
		when(repo.findById("1")).thenReturn(optT1);
		when(repo.save(tOne)).thenReturn(tOne);

		assertEquals(optT1, repo.findById("1"));
	}

	@Test
	public void testUpdateNonExisting()
	{
		Tour tOne = new Tour(
				"1", "Blurb", "TourName", "tourpackage", "tourBullets", "tourRegion", "tourdiff", 3, 1000f,
				new HashSet<String>(Arrays.asList("TEST1", "Test2")), "tourdesc"
		);
		Optional<Tour> optT1 = Optional.ofNullable(null);
		when(repo.findById("1")).thenReturn(optT1);
		when(repo.save(tOne)).thenReturn(null);
		
		assertThatExceptionOfType(RecordNotFoundException.class).isThrownBy(() -> service.update(tOne));
	}
}
