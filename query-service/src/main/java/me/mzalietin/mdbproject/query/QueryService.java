package me.mzalietin.mdbproject.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class QueryService {

    @Autowired
    DatabaseClient databaseClient;

}
