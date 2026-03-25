CREATE TABLE IF NOT EXISTS user_projection (
    username VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_projection (
    id VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    avg_rating DECIMAL NOT NULL
);

CREATE TABLE IF NOT EXISTS movie_review_projection (
    username VARCHAR(255) NOT NULL,
    movie_id VARCHAR(50) NOT NULL,
    movie_name VARCHAR(255) NOT NULL,
    rating INT NOT NULL
);
