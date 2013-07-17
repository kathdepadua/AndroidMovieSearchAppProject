package com.moviesearchapp.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.moviesearchapp.model.Movie;

public class SingleMovieHandler extends DefaultHandler {
	
	private StringBuffer buffer = new StringBuffer();
	
	private Movie movie;
	
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {

		buffer.setLength(0);
		
		if (localName.equals("movie")) {
			movie = new Movie();
		}
		
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)throws SAXException {
		
		if (localName.equals("name")) {
			movie.name = buffer.toString();
		}
		else if (localName.equals("imdb_id")) {
			movie.imdbId = buffer.toString();
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) {
		buffer.append(ch, start, length);
	}
	
	public Movie retrieveMovie() {
		return movie;
	}

}