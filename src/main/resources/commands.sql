drop table "user";
drop table places;
drop table bookings;

CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255)
);