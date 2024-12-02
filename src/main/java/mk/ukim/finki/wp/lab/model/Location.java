package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String capacity;
    private String description;

    @OneToMany(mappedBy = "location")
    private List<Event> events;
}
//import lombok.Data;
//
//@Data
//public class Location {
//    //Ne znam do kaj ste na auditoriski kaj vas ama mozi i so
//    //@GeneratedValue(Strategy=Identity) ili taka nesto
//    private Long id;
//    private String name;
//    private String address;
//    private String capacity;
//    private String description;
//
//    public Location(String name, String address, String capacity, String description) {
//        this.id = (long) (Math.random() * 1000);
//        this.name = name;
//        this.address = address;
//        this.capacity = capacity;
//        this.description = description;
//    }
//}
