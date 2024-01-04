package me.mzalietin.imdbproject.application;

import me.mzalietin.imdbproject.movie.config.MovieBeans;
import me.mzalietin.imdbproject.moviereview.config.MovieReviewBeans;
import me.mzalietin.imdbproject.user.config.UserBeans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    PersistenceConfig.class,
    MovieBeans.class,
    MovieReviewBeans.class,
    UserBeans.class
})
public class Beans {

//    @Bean
//    public LocalValidatorFactoryBean validator() {
//        return new LocalValidatorFactoryBean();
//    }
}
