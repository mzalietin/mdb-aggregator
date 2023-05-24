module imdb.aggregator.infrastructure {
    requires transitive imdb.aggregator.domain;
    requires spring.data.commons;
    requires spring.context;
    requires jakarta.persistence;
    exports com.mzaletsin.selfstudy.imdbaggregator.infrastructure;
    exports com.mzaletsin.selfstudy.imdbaggregator.infrastructure.repository to imdb.aggregator.application;
}
