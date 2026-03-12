package me.mzalietin.imdbproject;

import me.mzalietin.imdbproject.movie.MovieContextConfig;
import me.mzalietin.imdbproject.movierating.MovieReviewRatingConsumerConfig;
import me.mzalietin.imdbproject.moviereview.MovieReviewCrudConfig;
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
            .sibling(MovieReviewCrudConfig.class).web(WebApplicationType.SERVLET)
            .sibling(MovieReviewRatingConsumerConfig.class).web(WebApplicationType.SERVLET)
            .sibling(UserContextConfig.class).web(WebApplicationType.SERVLET)
            .run(args);
	}
}
