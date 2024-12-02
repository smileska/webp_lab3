//package mk.ukim.finki.wp.lab.web.Servlets;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.wp.lab.model.EventBooking;
//import mk.ukim.finki.wp.lab.service.EventBookingService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//
//@WebServlet(urlPatterns = "/eventBooking")
//public class EventBookingServlet extends HttpServlet {
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventBookingServlet(EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String eventName = req.getParameter("eventName");
//        String attendeeName = req.getParameter("attendeeName");
//        String attendeeAddress = req.getParameter("attendeeAddress");
//        String clientIp = req.getRemoteAddr();
//        int numTickets = Integer.parseInt(req.getParameter("numTickets"));
//
//        EventBooking booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numTickets);
//
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("booking", booking);
//        context.setVariable("clientIp", clientIp);
//
//        springTemplateEngine.process(
//                "bookingConfirmation.html",
//                context,
//                resp.getWriter()
//        );
//    }
//}
