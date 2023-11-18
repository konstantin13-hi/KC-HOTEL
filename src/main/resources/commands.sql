-- drop table users;
-- drop table places;
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

