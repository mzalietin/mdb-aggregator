CREATE TABLE IF NOT EXISTS mdb_user
(
    username varchar(30),
    first_name varchar(100) not null,
    last_name varchar(100),
    age integer not null,
    primary key (username)
);
