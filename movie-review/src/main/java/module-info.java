module imdbproject.moviereview {
    requires transitive imdbproject.movie;

    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;

//    exports me.mzalietin.imdbproject.moviereview.domain;
    exports me.mzalietin.imdbproject.moviereview.usecase.ports to imdbproject.application;
    exports me.mzalietin.imdbproject.moviereview.infrastructure to imdbproject.application;
}
