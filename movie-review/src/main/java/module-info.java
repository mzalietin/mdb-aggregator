module imdbproject.moviereview {
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    requires lombok;

    exports me.mzalietin.imdbproject.moviereview.config to imdbproject.application;
    exports me.mzalietin.imdbproject.moviereview.core.usecase.ports to imdbproject.application;
    exports me.mzalietin.imdbproject.moviereview.gateway.dataprovider to imdbproject.application;
}
