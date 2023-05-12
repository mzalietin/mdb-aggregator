module imdb.aggregator.infrastructure {
    requires transitive imdb.aggregator.domain;
    requires spring.data.commons;
    requires spring.context;
    requires jakarta.persistence;
}
