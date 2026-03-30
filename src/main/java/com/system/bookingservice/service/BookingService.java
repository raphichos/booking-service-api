package com.system.bookingservice.service;

import com.system.bookingservice.model.Booking;
import com.system.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public Optional<Booking> updateBooking(Long id, Booking newDetails) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setClientName(newDetails.getClientName());
            existingBooking.setServiceType(newDetails.getServiceType());
            existingBooking.setStatus(newDetails.getStatus());
            return bookingRepository.save(existingBooking);
        });
    }
}