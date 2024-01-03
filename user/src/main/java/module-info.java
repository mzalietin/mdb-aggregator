module imdbproject.user {
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    requires lombok;

    exports me.mzalietin.imdbproject.user.usecase.ports to imdbproject.application;
    exports me.mzalietin.imdbproject.user.infrastructure to imdbproject.application;
}
