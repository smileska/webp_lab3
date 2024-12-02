CREATE TABLE "public"."event" (
    "id" int8 NOT NULL,
    "description" varchar(255),
    "liked" bool NOT NULL DEFAULT false,
    "name" varchar(255),
    "popularity_score" float8,
    "location_id" int8,
    CONSTRAINT "fkbb6c0h5nhs5og47iem617ehrl" FOREIGN KEY ("location_id") REFERENCES "public"."location"("id"),
    PRIMARY KEY ("id")
);
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."event_booking" (
                                          "id" int8 NOT NULL,
                                          "attendee_address" varchar(255),
                                          "attendee_name" varchar(255),
                                          "event_name" varchar(255),
                                          "number_of_tickets" int8,
                                          PRIMARY KEY ("id")
);
-- This script only contains the table creation statements and does not fully represent the table in the database. Do not use it as a backup.

-- Table Definition
CREATE TABLE "public"."location" (
                                     "id" int8 NOT NULL,
                                     "address" varchar(255),
                                     "capacity" varchar(255),
                                     "description" varchar(255),
                                     "name" varchar(255),
                                     PRIMARY KEY ("id")
);


INSERT INTO location (name, address, capacity, description)
VALUES
    ('Central Park Arena', '123 Main St, New York, NY', 5000, 'An open-air arena in the heart of the city.'),
    ('Ocean View Hall', '456 Beach Ave, Miami, FL', 1000, 'A seaside hall with stunning ocean views.'),
    ('Mountain Ridge Amphitheater', '789 Hilltop Rd, Denver, CO', 3000, 'An amphitheater nestled in the mountains, ideal for large gatherings.'),
    ('Riverfront Pavilion', '101 River Rd, Austin, TX', 2000, 'A beautiful pavilion located on the riverfront.'),
    ('Desert Sky Venue', '202 Desert Blvd, Phoenix, AZ', 4000, 'An expansive venue under the desert sky.');

SELECT * FROM location;


INSERT INTO event (name, description, popularity_score, location_id, liked)
VALUES
    ('Arctic Monkeys Concert', 'Live in Manchester', 9.2, 1, false),
    ('Radiohead Concert', 'Special Night in Paris', 9.6, 2, false),
    ('The Strokes Concert', 'Open Air Performance in NYC', 8.9, 3, false),
    ('Foo Fighters Concert', 'Rock Night in LA', 9.0, 4, false),
    ('Slowdive Tribute Concert', 'Unplugged Experience', 8.3, 5, false),
    ('Fugazi Concert', 'Greatest Hits in Las Vegas', 8.7, 1, false),
    ('Oasis Tribute Concert', 'Britpop Revival in London', 8.5, 2, false),
    ('Muse Concert', 'Spectacular Show in Berlin', 9.3, 3, false),
    ('Smashing Pumpkins Concert', 'Alternative Rock Night', 8.4, 4, false),
    ('Depeche Mode Concert', 'Classic Electronic Hits', 9.1, 5, false);

SELECT * FROM event;
SELECT * FROM location;
