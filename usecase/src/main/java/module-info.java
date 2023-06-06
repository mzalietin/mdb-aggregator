module imdb.aggregator.usecase {
    requires transitive imdb.aggregator.domain;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase.port.in;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase.port.out;
    exports com.mzaletsin.selfstudy.imdbaggregator.usecase.exception;
}
