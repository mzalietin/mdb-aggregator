module imdb.aggregator.infrastructure {
    requires transitive imdb.aggregator.domain;
    requires spring.data.jpa;
    requires spring.context;
}
