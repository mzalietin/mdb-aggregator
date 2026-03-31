package me.mzalietin.mdbproject.queryservice.infrastructure.repo;

import java.math.BigDecimal;
import java.time.Duration;
import me.mzalietin.mdbproject.queryservice.domain.model.Movie;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.MovieRatingUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MovieDao extends BaseDao {

    @Autowired
    public MovieDao(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    public Mono<BigDecimal> ratingByName(String name) {
        return databaseClient.sql("select avg_rating from movie_projection where name=$1")
            .bind("$1", name)
            .mapValue(BigDecimal.class)
            .first();
    }

    public Flux<Movie> topByRating(Integer limit) {
        return databaseClient.sql("select * from movie_projection order by avg_rating desc limit $1")
            .bind("$1", limit)
            .mapProperties(Movie.class)
            .all();
    }

    public void save(String id, MovieCreated createdEvent) {
        databaseClient.sql("insert into movie_projection(id,name,avg_rating,reviews_count) values($1,$2,$3,$4) on conflict do nothing")
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
