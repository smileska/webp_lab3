package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findByPopularityScoreGreaterThanEqual(Double popularityScore);
    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(String name, String description, Double popularityScore);
    List<Event> findByNameContainingIgnoreCase(String name);
    List<Event> findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocation_Id(
            String name,
            Double popularityScore,
            Long locationId
    );
    List<Event> findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(
            String name,
            Double popularityScore
    );
    List<Event> findByPopularityScoreGreaterThanEqualAndLocation_Id(
            Double popularityScore,
            Long locationId
    );
    List<Event> findByNameContainingIgnoreCaseAndLocation_Id(
            String name,
            Long locationId
    );
}

//@Repository
//public class EventRepository {
//    private List<Event> events = new ArrayList<>();
//    private final LocationService locationService;
//    public EventRepository(LocationService locationService) {
//        this.locationService = locationService;
//        List<Location>locations=locationService.findAll();
//        events.add(new Event("Arctic Monkeys Concert", "Live in Manchester", 9.2, locations.get(0)));
//        events.add(new Event("Radiohead Concert", "Special Night in Paris", 9.6, locations.get(1)));
//        events.add(new Event("The Strokes Concert", "Open Air Performance in NYC", 8.9, locations.get(2)));
//        events.add(new Event("Foo Fighters Concert", "Rock Night in LA", 9.0, locations.get(3)));
//        events.add(new Event("Slowdive Tribute Concert", "Unplugged Experience", 8.3, locations.get(4)));
//        events.add(new Event("Fugazi Concert", "Greatest Hits in Las Vegas", 8.7, locations.get(0)));
//        events.add(new Event("Oasis Tribute Concert", "Britpop Revival in London", 8.5, locations.get(1)));
//        events.add(new Event("Muse Concert", "Spectacular Show in Berlin", 9.3, locations.get(2)));
//        events.add(new Event("Smashing Pumpkins Concert", "Alternative Rock Night", 8.4, locations.get(3)));
//        events.add(new Event("Depeche Mode Concert", "Classic Electronic Hits", 9.1, locations.get(4)));
//
//    }
//
//    public List<Event> findAll() {
//        return events;
//    }
//
//    public List<Event> searchEvents(String text, Double minRating) {
//        return events.stream()
//                .filter(e -> (text == null || e.getName().contains(text) || e.getDescription().contains(text)) &&
//                        (minRating == null || e.getPopularityScore() >= minRating))
//                .collect(Collectors.toList());
//    }
//    public Optional<Event> save( String name,  String description,  Double popularityScore,  Long locationId)
//    {
//        Location location=locationService.findById(locationId).get();
//        Event event=new Event(name,description,popularityScore,location);
//        events.add(event);
//        return Optional.of(event);
//    }
//    public Optional<Event> findById(Long id)
//    {
//        return events.stream().filter(i->i.getId().equals(id)).findFirst();
//    }
//    public Optional<Event>update(Long id, String name, String description,  Double popularityScore,  Long locationId)
//    {
//        Event event=findById(id).get();
//        event.setName(name);
//        event.setDescription(description);
//        event.setPopularityScore(popularityScore);
//        event.setLocation(locationService.findById(locationId).get());
//        return Optional.of(event);
//    }
//    public Optional<Event>delete(Long id)
//    {
//        Event event=findById(id).get();
//        events.removeIf(i->i.getId().equals(id));
//        return Optional.of(event);
//    }
//}
