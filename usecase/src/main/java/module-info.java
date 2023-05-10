module imdb.aggregator.usecase {
    requires jakarta.validation;
    requires transitive imdb.aggregator.domain;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase;
}
