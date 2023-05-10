module imdb.aggregator.application {
    requires imdb.aggregator.api;
    requires imdb.aggregator.infrastructure;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires spring.context;
}
