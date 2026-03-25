package me.mzalietin.mdbproject;

import me.mzalietin.mdbproject.query.QueryServiceConfig;
import me.mzalietin.mdbproject.movie.MovieContextConfig;
import me.mzalietin.mdbproject.movierating.MovieRatingAggregatorConfig;
import me.mzalietin.mdbproject.moviereview.MovieReviewContextConfig;
import me.mzalietin.mdbproject.user.UserContextConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootConfiguration
public class MdbAggregatorApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder()
            .parent(MdbAggregatorApplication.class).web(WebApplicationType.NONE)
            .child(MovieContextConfig.class).web(WebApplicationType.SERVLET)
            .sibling(MovieReviewContextConfig.class).web(WebApplicationType.SERVLET)
            .sibling(MovieRatingAggregatorConfig.class).web(WebApplicationType.NONE)
            .sibling(UserContextConfig.class).web(WebApplicationType.SERVLET)
            .sibling(QueryServiceConfig.class).web(WebApplicationType.REACTIVE)
            .run(args);
	}
}
