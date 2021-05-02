package com.milind.april28.assignment.model;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.hateoas.RepresentationModel;

@Document(collection = "tours")
public class Tour extends RepresentationModel<Tour>
{
	@Id
	private String id;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 500)
	private String tourBlurb;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 225)
	@Indexed
	private String tourName;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 50)
	private String tourPackage;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 100)
	private String tourBullets;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 50)
	private String tourRegion;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 20)
	private String tourDifficulty;

	@NotNull
	private Integer tourLength;

	private Float tourPrice;

	private Set<String> tourTags;

	@NotBlank
	@NotNull
	@Size(min = 5, max = 500)
	private String tourDescription;

	public Tour()
	{
		super();
	}

	public Tour(
			String tourBlurb, String tourName, String tourPackage, String tourBullets, String tourRegion,
			String tourDifficulty, Integer tourLength, Float tourPrice, Set<String> tourTags, String tourDescription
	)
	{
		super();
		this.tourBlurb = tourBlurb;
		this.tourName = tourName;
		this.tourPackage = tourPackage;
		this.tourBullets = tourBullets;
		this.tourRegion = tourRegion;
		this.tourDifficulty = tourDifficulty;
		this.tourLength = tourLength;
		this.tourPrice = tourPrice;
		this.tourTags = tourTags;
		this.tourDescription = tourDescription;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTourBlurb()
	{
		return tourBlurb;
	}

	public void setTourBlurb(String tourBlurb)
	{
		this.tourBlurb = tourBlurb;
	}

	public String getTourName()
	{
		return tourName;
	}

	public void setTourName(String tourName)
	{
		this.tourName = tourName;
	}

	public String getTourPackage()
	{
		return tourPackage;
	}

	public void setTourPackage(String tourPackage)
	{
		this.tourPackage = tourPackage;
	}

	public String getTourBullets()
	{
		return tourBullets;
	}

	public void setTourBullets(String tourBullets)
	{
		this.tourBullets = tourBullets;
	}

	public String getTourRegion()
	{
		return tourRegion;
	}

	public void setTourRegion(String tourRegion)
	{
		this.tourRegion = tourRegion;
	}

	public String getTourDifficulty()
	{
		return tourDifficulty;
	}

	public void setTourDifficulty(String tourDifficulty)
	{
		this.tourDifficulty = tourDifficulty;
	}

	public Integer getTourLength()
	{
		return tourLength;
	}

	public void setTourLength(Integer tourLength)
	{
		this.tourLength = tourLength;
	}

	public Float getTourPrice()
	{
		return tourPrice;
	}

	public void setTourPrice(Float tourPrice)
	{
		this.tourPrice = tourPrice;
	}

	public Set<String> getTourTags()
	{
		return tourTags;
	}

	public void setTourTags(Set<String> tourTags)
	{
		this.tourTags = tourTags;
	}

	public String getTourDescription()
	{
		return tourDescription;
	}

	public void setTourDescription(String tourDescription)
	{
		this.tourDescription = tourDescription;
	}

}
