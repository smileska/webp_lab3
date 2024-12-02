package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.EventBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<EventBooking, Long> {
    List<EventBooking> findByEventName(String eventName);
    List<EventBooking> findByAttendeeName(String attendeeName);
    List<EventBooking> findByAttendeeNameContainingIgnoreCase(String attendeeName);
}