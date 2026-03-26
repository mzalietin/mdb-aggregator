package me.mzalietin.mdbproject;

import static org.springframework.boot.Banner.Mode.OFF;

import me.mzalietin.mdbproject.queryservice.QueryServiceConfig;
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
            .child(MovieContextConfig.class).web(WebApplicationType.SERVLET).bannerMode(OFF)
            .sibling(MovieReviewContextConfig.class).web(WebApplicationType.SERVLET).bannerMode(OFF)
            .sibling(MovieRatingAggregatorConfig.class).web(WebApplicationType.NONE).bannerMode(OFF)
            .sibling(UserContextConfig.class).web(WebApplicationType.SERVLET).bannerMode(OFF)
            .sibling(QueryServiceConfig.class).web(WebApplicationType.REACTIVE).bannerMode(OFF)
            .run(args);
	}
}
