package me.mzalietin.mdbproject.queryservice.infrastructure.repo;

import java.time.Duration;
import me.mzalietin.mdbproject.queryservice.domain.model.User;
import me.mzalietin.mdbproject.queryservice.domain.model.event.UserCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserDao extends BaseDao {

    @Autowired
    public UserDao(final DatabaseClient databaseClient) {
        super(databaseClient);
    }

    public Mono<User> getUserInfo(String username) {
        return databaseClient.sql("select * from user_projection where username=$1")
            .bind("$1", username)
            .mapProperties(User.class)
            .first();
    }

    public void save(String username, UserCreated event) {
        databaseClient.sql("insert into user_projection(username,first_name,last_name,age) values($1,$2,$3,$4)")
            .bind("$1", username)
            .bind("$2", event.firstName())
            .bind("$3", event.lastName())
            .bind("$4", event.age())
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }

    public void delete(String username) {
        databaseClient.sql("delete from user_projection where username=$1")
            .bind("$1", username)
            .fetch()
            .rowsUpdated()
            .block(Duration.ofMillis(1000));
    }
}
