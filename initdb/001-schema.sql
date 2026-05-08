create table IF NOT EXISTS movie (
    id varchar(255) not null,
    average_rating numeric(4,2) not null,
    name varchar(100) not null,
    release_date date not null,
    reviews_count integer not null,
    primary key (id)
);

CREATE TABLE IF NOT EXISTS app_user
(
    username varchar(30),
    first_name varchar(100) not null,
    last_name varchar(100),
    age integer not null,
    primary key (username)
);

CREATE TABLE IF NOT EXISTS movie_review
(
    id bigserial primary key,
    username varchar(30) references app_user (username),
    movie_id varchar(255) references movie (id),
    rating integer not null check (rating > 0 and rating <= 10),
    comment varchar(5000),
    unique (username, movie_id)
);
