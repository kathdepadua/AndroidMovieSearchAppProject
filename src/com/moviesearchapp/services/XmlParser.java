package com.moviesearchapp.services;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.moviesearchapp.handlers.MovieHandler;
import com.moviesearchapp.handlers.PersonHandler;
import com.moviesearchapp.handlers.SingleMovieHandler;
import com.moviesearchapp.model.Movie;
import com.moviesearchapp.model.Person;

public class XmlParser {
	 private XMLReader initializeReader() throws ParserConfigurationException, SAXException {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        // create a parser
	        SAXParser parser = factory.newSAXParser();
	        // create the reader (scanner)
	        XMLReader xmlreader = parser.getXMLReader();
	        return xmlreader;
	    }
	    
	    public ArrayList<Person> parsePeopleResponse(String xml) {
	        
	        try {
	            
	            XMLReader xmlreader = initializeReader();
	            
	            PersonHandler personHandler = new PersonHandler();

	            // assign our handler
	            xmlreader.setContentHandler(personHandler);
	            // perform the synchronous parse
	            xmlreader.parse(new InputSource(new StringReader(xml)));
	            
	            return personHandler.retrievePersonList();
	            
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	        
	    }
	    
	    public ArrayList<Movie> parseMoviesResponse(String xml) {
	        
	        try {
	            
	            XMLReader xmlreader = initializeReader();
	            
	            MovieHandler movieHandler = new MovieHandler();

	            // assign our handler
	            xmlreader.setContentHandler(movieHandler);
	            // perform the synchronous parse
	            xmlreader.parse(new InputSource(new StringReader(xml)));
	            
	            return movieHandler.retrieveMoviesList();            
	            
	        } 
	        catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }

	    }
	    
	    public Movie parseSingleMovieResponse(String xml) {
			
			try {
				
				XMLReader xmlreader = initializeReader();
				
				SingleMovieHandler singleMovieHandler = new SingleMovieHandler();

				// assign our handler
				xmlreader.setContentHandler(singleMovieHandler);
				// perform the synchronous parse
				xmlreader.parse(new InputSource(new StringReader(xml)));
				
				return singleMovieHandler.retrieveMovie();	
				
			} 
			catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		}
}
