package com.moviesearchapp.services;

import java.util.ArrayList;

import android.util.Log;

import com.moviesearchapp.model.Movie;

public class MovieSeeker extends GenericSeeker<Movie> {
	private static final String MOVIE_SEARCH_PATH = "Movie.search/";
	private static final String LATEST_MOVIE_PATH = "Movie.getLatest/";
    
    public ArrayList<Movie> find(String query) {
        ArrayList<Movie> moviesList = retrieveMoviesList(query);
        return moviesList;
    }
    
    public ArrayList<Movie> find(String query, int maxResults) {
        ArrayList<Movie> moviesList = retrieveMoviesList(query);
        return retrieveFirstResults(moviesList, maxResults);
    }
    
    private ArrayList<Movie> retrieveMoviesList(String query) {
        String url = constructSearchUrl(query);
        String response = httpRetriever.retrieve(url);
        Log.d(getClass().getSimpleName(), response);
        return xmlParser.parseMoviesResponse(response);
    }

    @Override
    public String retrieveSearchMethodPath() {
        return MOVIE_SEARCH_PATH;
    }
    
    public Movie findLatest() {
		String url = constructLatestMovieSearchUrl();
		String response = httpRetriever.retrieve(url);
		return xmlParser.parseSingleMovieResponse(response);
	}
	
	private String constructLatestMovieSearchUrl() {
		StringBuffer sb = new StringBuffer();
		sb.append(BASE_URL);
		sb.append(LATEST_MOVIE_PATH);
		sb.append(LANGUAGE_PATH);
		sb.append(XML_FORMAT);
		sb.append(API_KEY);
		return sb.toString();
	}
}
