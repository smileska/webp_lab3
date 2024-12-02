//package mk.ukim.finki.wp.lab.web.Servlets;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.Event;
//import mk.ukim.finki.wp.lab.model.EventBooking;
//import mk.ukim.finki.wp.lab.service.EventBookingService;
//import mk.ukim.finki.wp.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "event-list-servlet", urlPatterns = "/servlet")
//public class EventListSevlet extends HttpServlet {
//    private final SpringTemplateEngine springTemplateEngine;
//    private final EventService eventService;
//    private final EventBookingService eventBookingService;
//    public EventListSevlet(SpringTemplateEngine springTemplateEngine, EventService eventService, EventBookingService eventBookingService) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.eventService = eventService;
//        this.eventBookingService = eventBookingService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        String searchText = req.getParameter("searchText");
//        String minRating = req.getParameter("minRating");
//        Double rating = minRating != null && !minRating.isEmpty() ? Double.valueOf(minRating) : null;
//
//        String attendeeSearch = req.getParameter("attendeeSearch");
//
//        List<Event> events;
//        if ((searchText != null && !searchText.isEmpty()) || rating != null) {
//            events = eventService.searchEvents(searchText, rating);
//        } else {
//            events = eventService.listAll();
//        }
//
//        List<EventBooking> bookings;
//        if (attendeeSearch != null && !attendeeSearch.isEmpty()) {
//            bookings = eventBookingService.getBookingsByAttendeeName(attendeeSearch);
//        } else {
//            bookings = eventBookingService.getAllBookings();
//        }
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("events", events);
//        context.setVariable("bookings", bookings);
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("/eventBooking");
//    }
//}