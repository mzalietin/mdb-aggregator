package me.mzalietin.mdbproject.query.repo;

import me.mzalietin.mdbproject.query.broker.event.ReviewCreated;
import me.mzalietin.mdbproject.query.broker.event.ReviewDeleted;
import me.mzalietin.mdbproject.query.broker.event.ReviewKey;
import me.mzalietin.mdbproject.query.broker.event.ReviewUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MovieReviewDao extends BaseDao {

    @Autowired
    public MovieReviewDao(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    public Mono<Long> save(ReviewKey key, ReviewCreated event) {
        return wrapIntoTransaction(c ->
            Mono.from(c.createStatement("select name from movie_projection where id = $1")
                    .bind("$1", key.movieId())
                    .execute()
                )
                .flatMap(result -> Mono.from(result.map(row -> row.get(0, String.class))))
                .flatMap(movieName ->
                    Mono.from(
                        c.createStatement("insert into movie_review_projection(username,movie_id,movie_name,rating) values($1,$2,$3,$4)")
                            .bind("$1", key.username())
                            .bind("$2", key.movieId())
                            .bind("$3", movieName)
                            .bind("$4", event.rating())
                            .execute())
                )
        )
            .flatMap(result -> Mono.from(result.getRowsUpdated()));
    }

    public Mono<Long> save(ReviewKey key, ReviewUpdated event) {
        return wrapIntoTransaction(
            c ->
                c.createStatement("update movie_review_projection set rating=$1 where username=$2 and movie_id=$3")
                    .bind("$1", event.newRating())
                    .bind("$2", key.username())
                    .bind("$3", key.movieId())
                    .execute())
            .flatMap(result -> Mono.from(result.getRowsUpdated()));
    }

    public Mono<Long> save(ReviewKey key, ReviewDeleted event) {
        return wrapIntoTransaction(
            c ->
                c.createStatement("delete from movie_review_projection where username=$1 and movie_id=$2")
                    .bind("$1", key.username())
                    .bind("$2", key.movieId())
                    .execute())
            .flatMap(result -> Mono.from(result.getRowsUpdated()));
    }
}
