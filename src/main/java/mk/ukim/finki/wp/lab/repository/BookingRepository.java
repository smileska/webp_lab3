package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookingRepository {
    private static List<EventBooking> bookings = new ArrayList<>();

    public EventBooking save(EventBooking booking) {
        bookings.add(booking);
        return booking;
    }

    public List<EventBooking> findAll() {
        return bookings;
    }

    public List<EventBooking> findByEventName(String eventName) {
        return bookings.stream()
                .filter(booking -> booking.getEventName().equals(eventName))
                .toList();
    }

    public List<EventBooking> findByAttendeeName(String attendeeName) {
        return bookings.stream()
                .filter(booking -> booking.getAttendeeName().equals(attendeeName))
                .toList();
    }
    public List<EventBooking> findByAttendeeNameContainingIgnoreCase(String attendeeName) {
        return bookings.stream()
                .filter(booking -> booking.getAttendeeName().toLowerCase()
                        .contains(attendeeName.toLowerCase()))
                .collect(Collectors.toList());
    }
}
