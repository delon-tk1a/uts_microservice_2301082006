package com.teknologiinformasi.restapi.booking.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.booking.model.Booking;
import com.teknologiinformasi.restapi.booking.service.BookingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/bookings")
public class BookingsController {

    private static final Logger log = LoggerFactory.getLogger(BookingsController.class);

   @Autowired
   private BookingService bookingsService;


   // Endpoint untuk mengambil semua produk
   @GetMapping
   public List<Booking> getAllBookings() {
    log.info("GET /api/bookings accessed");
       return bookingsService.getAllBooking();
   }


   // Endpoint untuk mengambil produk berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
    log.info("GET /api/bookings/{} accessed", id);
       return bookingsService.getBookingById(id)
               .map(bookings -> ResponseEntity.ok().body(bookings))
               .orElse(ResponseEntity.notFound().build());
   }


   // Endpoint untuk membuat produk baru
   @PostMapping
   public Booking createBookings(@RequestBody Booking bookings) {
    log.info("POST /api/bookings accessed with body: {}", bookings);
       return bookingsService.createBooking(bookings);
   }


   // Endpoint untuk mengupdate produk yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Booking> updateBookings(@PathVariable Long id, @RequestBody Booking bookingsDetails) {
    log.info("PUT /api/bookings/{} accessed with body: {}", id, bookingsDetails);
          
    try {
           Booking updatedBookings = bookingsService.updateBooking(id, bookingsDetails);
           return ResponseEntity.ok(updatedBookings);
       } catch (RuntimeException e) {
        log.warn("PUT /api/bookings/{} failed: {}", id, e.getMessage());
           return ResponseEntity.notFound().build();
       }
   }


//     Endpoint untuk menghapus produk
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBookings(@PathVariable Long id) {
        log.info("DELETE /api/bookings/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            bookingsService.deleteBooking(id);
            response.put("message", "Bookings berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Bookings tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/bookings/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}