package me.mzalietin.mdbproject.queryservice.infrastructure.repo;

import io.r2dbc.spi.Connection;
import io.r2dbc.spi.Result;
import java.util.function.Function;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.r2dbc.core.DatabaseClient;

public abstract class BaseDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final DatabaseClient databaseClient;

    public BaseDao(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    protected void runInTransaction(Function<Connection, Publisher<? extends Result>> operation) {
        //todo
    }
}
