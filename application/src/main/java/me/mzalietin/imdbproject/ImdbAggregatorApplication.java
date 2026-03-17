package me.mzalietin.imdbproject;

import me.mzalietin.imdbproject.movie.MovieContextConfig;
import me.mzalietin.imdbproject.movierating.MovieRatingAggregatorConfig;
import me.mzalietin.imdbproject.moviereview.MovieReviewContextConfig;
import me.mzalietin.imdbproject.user.UserContextConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootConfiguration
public class ImdbAggregatorApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder()
            .parent(ImdbAggregatorApplication.class).web(WebApplicationType.NONE)
            .child(MovieContextConfig.class).web(WebApplicationType.SERVLET)
            .sibling(MovieReviewContextConfig.class).web(WebApplicationType.SERVLET)
            .sibling(MovieRatingAggregatorConfig.class).web(WebApplicationType.NONE)
            .sibling(UserContextConfig.class).web(WebApplicationType.SERVLET)
            .run(args);
	}
}
