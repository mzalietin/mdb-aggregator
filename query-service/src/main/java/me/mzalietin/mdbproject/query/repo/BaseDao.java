package me.mzalietin.mdbproject.query.repo;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.Result;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

public abstract class BaseDao {

    protected final DatabaseClient databaseClient;

    public BaseDao(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    protected Mono<Result> wrapIntoTransaction(Function<Connection, Publisher<? extends Result>> operation) {
        return Mono.from(databaseClient.getConnectionFactory().create())
            .flatMap(c -> Mono.from(c.beginTransaction())
                .then(Mono.from(operation.apply(c)))
                .delayUntil(r -> c.commitTransaction())
                .doFinally((st) -> c.close())
            );
    }
}
