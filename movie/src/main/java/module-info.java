module imdbproject.movie {
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    requires lombok;

    exports me.mzalietin.imdbproject.movie.config to imdbproject.application;
    exports me.mzalietin.imdbproject.movie.core.usecase.ports to imdbproject.application;
    exports me.mzalietin.imdbproject.movie.gateway.dataprovider to imdbproject.application;
}
