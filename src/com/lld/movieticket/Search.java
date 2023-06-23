package com.lld.movieticket;

import java.util.Date;
import java.util.List;

public interface Search {
	public List<Movie> searchByTitle(String title);
	public List<Movie> searchByLanguage(String language);
	public List<Movie> searchByGenre(String genre);
	public List<Movie> searchByRekeaseDate(Date delDate);
	public List<Movie> searchByCity(String cityName);
}

