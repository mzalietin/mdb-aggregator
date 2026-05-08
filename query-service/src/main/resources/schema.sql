CREATE TABLE IF NOT EXISTS user_projection (
    username VARCHAR(30) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_projection (
    id bigint PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    avg_rating DECIMAL NOT NULL,
    reviews_count INT NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_review_projection (
    id VARCHAR(255) PRIMARY KEY,
    username VARCHAR(30) NOT NULL,
    movie_id bigint NOT NULL,
    rating INT NOT NULL
);
