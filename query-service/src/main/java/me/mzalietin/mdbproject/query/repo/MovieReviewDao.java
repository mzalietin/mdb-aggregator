package me.mzalietin.mdbproject.query.repo;

import java.time.Duration;
import me.mzalietin.mdbproject.query.broker.event.ReviewCreated;
import me.mzalietin.mdbproject.query.broker.event.ReviewDeleted;
import me.mzalietin.mdbproject.query.broker.event.ReviewKey;
import me.mzalietin.mdbproject.query.broker.event.ReviewUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewDao extends BaseDao {

    @Autowired
    public MovieReviewDao(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    public void save(ReviewKey key, ReviewCreated event) {
        databaseClient.sql("insert into movie_review_projection(username,movie_id,rating) values($1,$2,$3)")
            .bind("$1", key.username())
            .bind("$2", key.movieId())
            .bind("$3", event.rating())
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }

    public void save(ReviewKey key, ReviewUpdated event) {
        databaseClient.sql("update movie_review_projection set rating=$1 where username=$2 and movie_id=$3")
            .bind("$1", event.newRating())
            .bind("$2", key.username())
            .bind("$3", key.movieId())
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }

    public void save(ReviewKey key, ReviewDeleted event) {
        databaseClient.sql("delete from movie_review_projection where username=$1 and movie_id=$2")
            .bind("$1", key.username())
            .bind("$2", key.movieId())
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }
}
