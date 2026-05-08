create table IF NOT EXISTS movie (
    id bigserial primary key,
    average_rating numeric(4,2) not null,
    name varchar(100) not null,
    release_date date not null,
    reviews_count integer not null
);

CREATE TABLE IF NOT EXISTS app_user
(
    username varchar(30) primary key,
    first_name varchar(100) not null,
    last_name varchar(100),
    age integer not null
);

CREATE TABLE IF NOT EXISTS movie_review
(
    id varchar(255) primary key default gen_random_uuid(),
    username varchar(30) references app_user (username),
    movie_id bigint references movie (id),
    rating integer not null check (rating > 0 and rating <= 10),
    comment varchar(5000),
    unique (username, movie_id)
);
