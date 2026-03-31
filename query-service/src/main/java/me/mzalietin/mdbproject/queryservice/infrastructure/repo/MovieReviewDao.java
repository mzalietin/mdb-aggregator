package me.mzalietin.mdbproject.queryservice.infrastructure.repo;

import java.math.BigDecimal;
import java.time.Duration;
import me.mzalietin.mdbproject.queryservice.domain.model.Movie;
import me.mzalietin.mdbproject.queryservice.domain.model.MovieWithUserRating;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewCreated;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewKey;
import me.mzalietin.mdbproject.queryservice.domain.model.event.ReviewUpdated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class MovieReviewDao extends BaseDao {

    @Autowired
    public MovieReviewDao(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    public void save(ReviewKey key, ReviewCreated event) {
        databaseClient.sql("insert into movie_review_projection(username,movie_id,rating) values($1,$2,$3) on conflict do nothing")
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

    public void delete(ReviewKey key) {
        databaseClient.sql("delete from movie_review_projection where username=$1 and movie_id=$2")
            .bind("$1", key.username())
            .bind("$2", key.movieId())
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }

    public Flux<MovieWithUserRating> topByUser(String username, Integer limit) {
        return databaseClient.sql("""
                select m.id, m.name, m.avg_rating, mr.rating as user_rating, m.reviews_count from movie_review_projection mr join movie_projection m on mr.movie_id = m.id
                where mr.username = $1
                order by mr.rating desc
                limit $2
                """)
            .bind("$1", username)
            .bind("$2", limit)
            .map(readable -> new MovieWithUserRating(
                readable.get("user_rating", Integer.class),
                new Movie(
                    readable.get("id", String.class),
                    readable.get("name", String.class),
                    readable.get("avg_rating", BigDecimal.class),
                    readable.get("reviews_count", Integer.class)
                )))
            .all();
    }
}
