module imdbproject.application {
    requires imdbproject.movie;
    requires imdbproject.moviereview;
    requires imdbproject.user;

    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires jakarta.persistence;
}
