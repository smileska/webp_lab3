package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double popularityScore;
    private Boolean liked = false;

    @ManyToOne
    private Location location;

}

//@Data
//public class Event {
//    private Long id;
//    String name;
//    String description;
//    double popularityScore;
//    private Location location;
//
//    public Event(){
//        this.id = null;
//        this.name = null;
//        this.description = null;
//        this.popularityScore = 0;
//        this.location = null;
//    }
//
//    public Event(String name, String description, double popularityScore, Location location) {
//        this.id = (long) (Math.random() * 1000);
//        this.name = name;
//        this.description = description;
//        this.popularityScore = popularityScore;
//        this.location = location;
//    }
//}
