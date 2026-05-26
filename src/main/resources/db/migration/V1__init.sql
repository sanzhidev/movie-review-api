CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
CREATE TABLE genres (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE movies (
                        id BIGSERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        overview TEXT,
                        release_date DATE,
                        poster_path VARCHAR(500),
                        tmdb_id INTEGER UNIQUE,
                        average_rating DECIMAL(3,1) DEFAULT 0.0,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
CREATE TABLE movie_genres (
                              movie_id BIGINT REFERENCES movies(id) ON DELETE CASCADE,
                              genre_id BIGINT REFERENCES genres(id) ON DELETE CASCADE,
                              PRIMARY KEY (movie_id, genre_id)
);

CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                         movie_id BIGINT NOT NULL REFERENCES movies(id) ON DELETE CASCADE,
                         rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 10),
                         content TEXT,
                         created_at TIMESTAMP NOT NULL DEFAULT NOW(),
                         UNIQUE (user_id, movie_id)
);