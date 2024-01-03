module imdbproject.moviereview {
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    requires lombok;

    exports me.mzalietin.imdbproject.moviereview.usecase.ports to imdbproject.application;
    exports me.mzalietin.imdbproject.moviereview.infrastructure to imdbproject.application;
}
