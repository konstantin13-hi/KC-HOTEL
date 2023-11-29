-- drop table users;
-- drop table user_roles;
-- drop table places;
-- drop table place_photos;
-- drop table place_perks;
-- drop table bookings;

CREATE TABLE Users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       password VARCHAR(255)
);

CREATE TABLE Places (
                        id SERIAL PRIMARY KEY,
                        owner_id INTEGER REFERENCES users(id),
                        title VARCHAR(255),
                        address VARCHAR(255),
                        photos TEXT[],
                        description TEXT,
                        perks TEXT[],
                        extra_info TEXT,
                        check_in INTEGER,
                        check_out INTEGER,
                        max_guests INTEGER,
                        price NUMERIC
);


CREATE TABLE Bookings (
                          id SERIAL PRIMARY KEY,
                          place_id INTEGER REFERENCES places(id) NOT NULL,
                          user_id INTEGER REFERENCES users(id) NOT NULL,
                          check_in DATE NOT NULL,
                          check_out DATE NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          phone VARCHAR(255) NOT NULL,
                          price NUMERIC,
                          number_of_guests INTEGER NOT NULL,
                          CONSTRAINT valid_dates CHECK (check_in < check_out)
);

