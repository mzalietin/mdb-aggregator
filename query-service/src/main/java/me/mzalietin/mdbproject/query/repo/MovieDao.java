package me.mzalietin.mdbproject.query.repo;

import java.time.Duration;
import me.mzalietin.mdbproject.query.broker.event.MovieCreated;
import me.mzalietin.mdbproject.query.broker.event.MovieRatingUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class MovieDao extends BaseDao {

    @Autowired
    public MovieDao(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    public void save(String id, MovieCreated createdEvent) {
        databaseClient.sql("insert into movie_projection(id,name,avg_rating,reviews_count) values($1,$2,$3,$4)")
            .bind("$1", id)
            .bind("$2", createdEvent.name())
            .bind("$3", createdEvent.rating())
            .bind("$4", createdEvent.reviewsCount())
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }

    public void save(String id, MovieRatingUpdated updatedEvent) {
        databaseClient.sql("update movie_projection set avg_rating=$1, reviews_count=$2 where id=$3")
            .bind("$1", updatedEvent.averageRating())
            .bind("$2", updatedEvent.reviewsCount())
            .bind("$3", id)
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }
}
