package com.teknologiinformasi.restapi.booking.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teknologiinformasi.restapi.booking.model.Booking;
import com.teknologiinformasi.restapi.booking.respository.BookingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        booking.setOrderNumber(bookingDetails.getOrderNumber());
        booking.setAmount(bookingDetails.getAmount());
        booking.setStatus(bookingDetails.getStatus());
        booking.setBookingDate(bookingDetails.getBookingDate());
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
        bookingRepository.delete(booking);
    }
}