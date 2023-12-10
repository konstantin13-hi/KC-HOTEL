drop table users;
drop table places;
drop table bookings;
  drop table user_user_roles;
  drop table place_photos;
  drop table place_perks;

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



CREATE TABLE user_roles (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL
);

INSERT INTO user_roles (name) VALUES ('ROLE_USER');

INSERT INTO user_roles (name) VALUES ('ROLE_ADMIN');

INSERT INTO Users (name, email, password)
VALUES ('user100', 'example@email.com', '$2a$10$4VNsjdzIvZ8L5GtUSEv9dud6nWck4zzY5ffPSIy/s54r6vhE83tuK');


insert into user_user_roles(user_id, role_id)
values (14,2);