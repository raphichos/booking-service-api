package com.system.bookingservice.controller;

import com.system.bookingservice.model.Booking;
import com.system.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking){
        return bookingRepository.save(booking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking newBookingData) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setClientName(newBookingData.getClientName());
                    booking.setServiceType(newBookingData.getServiceType());
                    booking.setStatus(newBookingData.getStatus());
                    return bookingRepository.save(booking);
                })
                .orElseGet(() -> {
                    newBookingData.setId(id);
                    return bookingRepository.save(newBookingData);
                });
    }
}