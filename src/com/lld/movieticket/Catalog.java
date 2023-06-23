package com.lld.movieticket;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Catalog implements Search {

	HashMap<String, List<Movie>> movieTitles;
	HashMap<String, List<Movie>> movieLanguages;
	HashMap<String, List<Movie>> movieGenres;
	HashMap<Date, List<Movie>> movieReleaseDates;
	HashMap<String, List<Movie>> movieCities;

	@Override
	public List<Movie> searchByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> searchByLanguage(String language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> searchByGenre(String genre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> searchByRekeaseDate(Date delDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> searchByCity(String cityName) {
		// TODO Auto-generated method stub
		return null;
	}

}
