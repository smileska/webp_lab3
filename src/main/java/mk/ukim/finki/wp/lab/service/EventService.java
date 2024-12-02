package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, Double minRating, Long locationId);
    Optional<Event> findById(Long id);
    Optional<Event> delete(Long id);
    Optional<Event> likeEvent(Long id);
    List<Event> findAllByLocation_Id(Long locationId);
    Optional<Event> save(String name, String description, Double popularityScore, Long locationId, Long categoryId);
    Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId, Long categoryId);
}




