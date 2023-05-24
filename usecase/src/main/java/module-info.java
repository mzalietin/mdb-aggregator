module imdb.aggregator.usecase {
    requires transitive imdb.aggregator.domain;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase.implementation to imdb.aggregator.application;
}
