package com.teknologiinformasi.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teknologiinformasi.restapi.model.Movie;
import com.teknologiinformasi.restapi.repository.MovieRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MovieService {


   @Autowired
   private MovieRepository movieRepository;


   public List<Movie> getAllMovie() {
       return movieRepository.findAll();
   }


   public Optional<Movie> getMovieById(Long id) {
       return movieRepository.findById(id);
   }


   public Movie createMovie(Movie movie) {
       return movieRepository.save(movie);
   }


   public Movie updateMovie(Long id, Movie movieDetails) {
       Movie movie = movieRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Movie tidak ditemukan dengan id " + id));
       movie.setNama(movieDetails.getNama());
       movie.setHarga(movieDetails.getHarga());
       movie.setDeskripsi(movieDetails.getDeskripsi());
       return movieRepository.save(movie);
   }


   public void deleteMovie(Long id) {
       Movie movie = movieRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Movie tidak ditemukan dengan id " + id));
       movieRepository.delete(movie);
   }
}
