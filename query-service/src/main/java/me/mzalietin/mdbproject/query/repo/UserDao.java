package me.mzalietin.mdbproject.query.repo;

import me.mzalietin.mdbproject.query.broker.event.UserCreated;
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

    public Mono<?> save(String username, UserCreated event) {
        return wrapIntoTransaction(
            c -> c.createStatement("insert into user_projection(username,first_name,last_name,age) values($1,$2,$3,$4)")
                .bind("$1", username)
                .bind("$2", event.firstName())
                .bind("$3", event.lastName())
                .bind("$4", event.age())
                .execute()
        );
    }

    public Mono<?> delete(String username) {
        return wrapIntoTransaction(
            c ->
                c.createStatement("delete from user_projection where username=$1")
                    .bind("$1", username)
                    .execute()
        );
    }
}
