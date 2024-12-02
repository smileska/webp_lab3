package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.repository.BookingRepository;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {

    private final BookingRepository bookingRepository;

    public EventBookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking booking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        return bookingRepository.save(booking);
    }

    @Override
    public List<EventBooking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<EventBooking> getBookingsByEventName(String eventName) {
        return bookingRepository.findByEventName(eventName);
    }

    @Override
    public List<EventBooking> getBookingsByAttendeeName(String attendeeName) {
        return bookingRepository.findByAttendeeName(attendeeName);
    }
    @Override
    public List<EventBooking> searchBookings(String attendeeName) {
        if (attendeeName == null || attendeeName.isEmpty()) {
            return getAllBookings();
        }
        return bookingRepository.findByAttendeeNameContainingIgnoreCase(attendeeName);
    }
}
