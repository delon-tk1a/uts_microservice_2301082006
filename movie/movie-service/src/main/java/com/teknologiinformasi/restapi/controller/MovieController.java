package com.teknologiinformasi.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.model.Movie;
import com.teknologiinformasi.restapi.service.MovieService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/movie")
public class MovieController {

    private static final Logger log = LoggerFactory.getLogger(MovieController.class);

   @Autowired
   private MovieService movieService;


   // Endpoint untuk mengambil semua movie
   @GetMapping
   public List<Movie> getAllMovie() {
    log.info("GET /api/movie accessed");
       return movieService.getAllMovie();
   }


   // Endpoint untuk mengambil movie berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
    log.info("GET /api/movie/{} accessed", id);
       return movieService.getMovieById(id)
               .map(movie -> ResponseEntity.ok().body(movie))
               .orElse(ResponseEntity.notFound().build());
   }


   // Endpoint untuk membuat movie baru
   @PostMapping
   public Movie createMovie(@RequestBody Movie movie) {
    log.info("POST /api/movie accessed with body: {}", movie);
       return movieService.createMovie(movie);
   }


   // Endpoint untuk mengupdate movie yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
    log.info("PUT /api/movie/{} accessed with body: {}", id, movieDetails);
          
    try {
           Movie updatedMovie = movieService.updateMovie(id, movieDetails);
           return ResponseEntity.ok(updatedMovie);
       } catch (RuntimeException e) {
        log.warn("PUT /api/movie/{} failed: {}", id, e.getMessage());
           return ResponseEntity.notFound().build();
       }
   }


//     Endpoint untuk menghapus movie
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable Long id) {
        log.info("DELETE /api/movie/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            movieService.deleteMovie(id);
            response.put("message", "Movie berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Movie tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/movie/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
