CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255) UNIQUE,
                       password VARCHAR(255)
);

CREATE TABLE places (
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

CREATE TABLE bookings (
                          id SERIAL PRIMARY KEY,
                          place_id INTEGER REFERENCES places(id) NOT NULL,
                          user_id INTEGER NOT NULL,
                          check_in TIMESTAMPTZ NOT NULL,
                          check_out TIMESTAMPTZ NOT NULL,
                          name VARCHAR(255) NOT NULL,
                          phone VARCHAR(255) NOT NULL,
                          price NUMERIC,
                          CONSTRAINT fk_place FOREIGN KEY (place_id) REFERENCES places(id),
                          CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);