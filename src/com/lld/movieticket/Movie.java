package com.lld.movieticket;

import java.sql.Date;
import java.util.List;

import com.lld.movieticket.account.Admin;

public class Movie {

	private String title;
	private String description;
	private int durationInMins;
	private String language;
	private Date releaseDate;
	private String country;
	private String genre;
	private Admin movieAddedBy;
	private List<Show> shows;

}
