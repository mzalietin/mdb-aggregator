module imdbproject.movie {
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    requires lombok;

    exports me.mzalietin.imdbproject.movie.usecase.ports to imdbproject.application;
    exports me.mzalietin.imdbproject.movie.infrastructure to imdbproject.application;
}
