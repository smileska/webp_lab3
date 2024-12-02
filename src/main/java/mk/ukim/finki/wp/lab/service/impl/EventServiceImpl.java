package mk.ukim.finki.wp.lab.service.impl;

import java.util.List;
import java.util.Optional;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.repository.EventRepository;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Service;
import mk.ukim.finki.wp.lab.model.Location;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationService locationService;

    public EventServiceImpl(EventRepository eventRepository, LocationService locationService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double minRating, Long locationId) {
        if ((text == null || text.isEmpty()) && minRating == null && locationId == null) {
            return eventRepository.findAll();
        }
        if ((text == null || text.isEmpty()) && minRating == null && locationId != null) {
            return eventRepository.findAllByLocation_Id(locationId);
        }
        if (text != null && !text.isEmpty() && minRating == null && locationId == null) {
            return eventRepository.findByNameContainingIgnoreCase(text);
        }
        if ((text == null || text.isEmpty()) && minRating != null && locationId == null) {
            return eventRepository.findByPopularityScoreGreaterThanEqual(minRating);
        }
        if (text != null && !text.isEmpty() && minRating != null && locationId == null) {
            return eventRepository.findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(text, minRating);
        }
        if (text != null && !text.isEmpty() && minRating == null && locationId != null) {
            return eventRepository.findByNameContainingIgnoreCaseAndLocation_Id(text, locationId);
        }
        if ((text == null || text.isEmpty()) && minRating != null && locationId != null) {
            return eventRepository.findByPopularityScoreGreaterThanEqualAndLocation_Id(minRating, locationId);
        }
        return eventRepository.findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocation_Id(
                text, minRating, locationId);
    }

    @Override
    public Optional<Event> save(String name, String description, Double popularityScore, Long locationId) {
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);
        Optional<Location> location = locationService.findById(locationId);
        if (location.isPresent()) {
            event.setLocation(location.get());
        } else {
            throw new IllegalArgumentException("Invalid location ID: " + locationId);
        }

        eventRepository.save(event);
        return Optional.of(event);
    }

    @Override
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            return Optional.of(eventRepository.save(event));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Event> delete(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        eventOptional.ifPresent(eventRepository::delete);
        return eventOptional;
    }
    @Override
    public Optional<Event> likeEvent(Long id) {
        Optional<Event> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            if (!event.getLiked()) {
                event.setLiked(true);
                event.setPopularityScore(event.getPopularityScore() + 0.5);
                return Optional.of(eventRepository.save(event));
            }
        }
        return eventOptional;
    }

    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

}

//@Service
//public class EventServiceImpl implements EventService {
//    private final EventRepository eventRepository;
//
//    public EventServiceImpl(EventRepository eventRepository) {
//        this.eventRepository = eventRepository;
//    }
//
//    public List<Event> listAll() {
//        return eventRepository.findAll();
//    }
//
//    @Override
//    public List<Event> searchEvents(String text, Double minRating) {
//        return eventRepository.searchEvents(text, minRating);
//    }
//
//    @Override
//    public Optional<Event> save(String name, String description, Double popularityScore, Long locationId) {
//        return eventRepository.save(name, description, popularityScore, locationId);
//    }
//
//    @Override
//    public Optional<Event> findById(Long id) {
//        return  eventRepository.findById(id);
//    }
//
//    @Override
//    public Optional<Event> update(Long id, String name, String description, Double popularityScore, Long locationId) {
//        return eventRepository.update(id, name, description, popularityScore, locationId);
//    }
//
//    @Override
//    public Optional<Event> delete(Long id) {
//        return eventRepository.delete(id);
//    }
//}
//
//
//    public List<Event> searchEvents(String text) {
//        return eventRepository.searchEvents(text);
////    }
//}
