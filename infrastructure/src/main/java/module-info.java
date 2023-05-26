module imdb.aggregator.infrastructure {
    requires transitive imdb.aggregator.domain;
    requires spring.data.commons;
    requires spring.data.jpa;
    requires spring.context;
    requires jakarta.persistence;
    exports com.mzaletsin.selfstudy.imdbaggregator.infrastructure;
}
