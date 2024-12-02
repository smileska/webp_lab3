package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

//import mk.ukim.finki.wp.lab.model.Location;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class LocationRepository {
//private  List<Location> locations=new ArrayList<>();
//
//public LocationRepository() {
//
//    locations.add(new Location("Central Park Arena", "123 Main St, New York, NY", "5000", "An open-air arena in the heart of the city."));
//    locations.add(new Location("Ocean View Hall", "456 Beach Ave, Miami, FL", "1000", "A seaside hall with stunning ocean views."));
//    locations.add(new Location("Mountain Ridge Amphitheater", "789 Hilltop Rd, Denver, CO", "3000", "An amphitheater nestled in the mountains, ideal for large gatherings."));
//    locations.add(new Location("Riverfront Pavilion", "101 River Rd, Austin, TX", "2000", "A beautiful pavilion located on the riverfront."));
//    locations.add(new Location("Desert Sky Venue", "202 Desert Blvd, Phoenix, AZ", "4000", "An expansive venue under the desert sky."));
//
//
//}
//public List<Location>findAll()
//{
//    return locations;
//}
//public Optional<Location> findById(Long id)
//{
//    return locations.stream().filter(location -> location.getId().equals(id)).findFirst();
//}
//}
