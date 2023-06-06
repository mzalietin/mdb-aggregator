module imdb.aggregator.infrastructure {
    requires transitive imdb.aggregator.domain;
    requires transitive imdb.aggregator.usecase;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    requires spring.boot.autoconfigure;
    exports com.mzaletsin.selfstudy.imdbaggregator.infrastructure;
}
