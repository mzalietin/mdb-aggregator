package me.mzalietin.mdbproject.query.repo;

import me.mzalietin.mdbproject.query.broker.event.MovieCreated;
import me.mzalietin.mdbproject.query.broker.event.MovieRatingUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MovieDao {

    @Autowired
    DatabaseClient databaseClient;

    public Mono<Long> save(String id, MovieCreated createdEvent) {
        return Mono.from(databaseClient.getConnectionFactory().create())
            .flatMap(c -> Mono.from(c.beginTransaction())
                .then(Mono.from(c.createStatement("insert into movie_projection(id,name,avg_rating,reviews_count) values($1,$2,$3,$4)")
                    .bind("$1", id)
                    .bind("$2", createdEvent.name())
                    .bind("$3", createdEvent.rating())
                    .bind("$4", createdEvent.reviewsCount())
                    .execute())
                )
                .delayUntil(r -> c.commitTransaction())
                .doFinally((st) -> c.close()))
            .flatMap(result -> Mono.from(result.getRowsUpdated()));
    }

    public Mono<Long> save(String id, MovieRatingUpdated updatedEvent) {
        return Mono.from(databaseClient.getConnectionFactory().create())
            .flatMap(c -> Mono.from(c.beginTransaction())
                .then(Mono.from(c.createStatement("update movie_projection set avg_rating=$1, reviews_count=$2 where id=$3")
                    .bind("$1", updatedEvent.averageRating())
                    .bind("$2", updatedEvent.reviewsCount())
                    .bind("$3", id)
                    .execute())
                )
                .delayUntil(r -> c.commitTransaction())
                .doFinally((st) -> c.close()))
            .flatMap(result -> Mono.from(result.getRowsUpdated()));
    }
}
