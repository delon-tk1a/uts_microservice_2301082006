package com.teknologiinformasi.restapi.booking.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teknologiinformasi.restapi.booking.model.Booking;
 

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}