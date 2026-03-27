package me.mzalietin.mdbproject;

import static org.springframework.boot.Banner.Mode.OFF;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import me.mzalietin.mdbproject.movie.MovieContextConfig;
import me.mzalietin.mdbproject.movierating.MovieRatingAggregatorConfig;
import me.mzalietin.mdbproject.moviereview.MovieReviewContextConfig;
import me.mzalietin.mdbproject.queryservice.QueryServiceConfig;
import me.mzalietin.mdbproject.user.UserContextConfig;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class MdbAggregatorApplication {

    @Value("${kafka.host}")
    String kafkaHost;

    // non Prod config

    @Bean
    public AdminClient admin() throws Exception {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        AdminClient client = AdminClient.create(configs);

        NewTopic userEvents = new NewTopic("user-events", 1,  (short) 1);
        NewTopic reviewEvents = new NewTopic("movie-review-events", 1,  (short) 1);
        NewTopic ratingOutput = new NewTopic("movie-rating-output", 1,  (short) 1);
        NewTopic movieEvents = new NewTopic("movie-events", 1,  (short) 1);
        NewTopic movieConsumerDlt = new NewTopic("movie-context-consumer-DLT", 1,  (short) 1);

        client.createTopics(List.of(userEvents, reviewEvents, ratingOutput, movieEvents, movieConsumerDlt))
            .all()
            .get(5, TimeUnit.SECONDS);

        return client;
    }

    // ------

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
