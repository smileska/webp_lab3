package mk.ukim.finki.wp.lab.web.controller;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.EventBooking;
import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.EventService;
import mk.ukim.finki.wp.lab.service.LocationService;
import org.springframework.ui.Model;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/","/events"})
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;
    private final EventBookingService eventBookingService;

    public EventController(EventService eventService, LocationService locationService, EventBookingService eventBookingService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String getEventsPage(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) Long locationId,
            @RequestParam(required = false) String attendeeSearch,
            @RequestParam(required = false) String error,
            Model model) {

        List<Event> eventList = eventService.searchEvents(searchText, minRating, locationId);
        List<EventBooking> bookings = attendeeSearch != null ?
                eventBookingService.searchBookings(attendeeSearch) :
                eventBookingService.getAllBookings();

        model.addAttribute("searchText", searchText);
        model.addAttribute("minRating", minRating);
        model.addAttribute("selectedLocationId", locationId);
        model.addAttribute("attendeeSearch", attendeeSearch);
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("bookings", bookings);
        model.addAttribute("events", eventList);
        model.addAttribute("error", error);
        return "listEvents";
    }

    @GetMapping("/add")
    public String showAddEventForm(Model model) {
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("event", new Event());
        return "addEvent";
    }

    @GetMapping("/{id}/edit")
    public String showEditEventForm(@PathVariable Long id, Model model) {
        Event event = eventService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event ID:" + id));
        model.addAttribute("event", event);
        model.addAttribute("locations", locationService.findAll());
        return "addEvent";
    }
    @GetMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId, Model model) {
        // Retrieve the event based on the ID
        Optional<Event> eventOpt = eventService.findById(eventId);

        if (eventOpt.isPresent()) {
            Event event = eventOpt.get();
            model.addAttribute("event", event);
            model.addAttribute("locations", locationService.findAll());
            return "addEvent";
        } else {
            model.addAttribute("error", "Event with the specified ID does not exist.");
            return "redirect:/events";
        }
    }

    @PostMapping("/save")
    public String saveOrUpdateEvent(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double popularityScore,
            @RequestParam Long locationId) {

        if (id == null) {
            eventService.save(name, description, popularityScore, locationId);
        } else {
            eventService.update(id, name, description, popularityScore, locationId);
        }
        return "redirect:/events";
    }
    @PostMapping("/{id}/delete")
    public String deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return "redirect:/events";
    }
    @PostMapping("/{id}/like")
    public String likeEvent(@PathVariable Long id) {
        eventService.likeEvent(id);
        return "redirect:/events";
    }

}

