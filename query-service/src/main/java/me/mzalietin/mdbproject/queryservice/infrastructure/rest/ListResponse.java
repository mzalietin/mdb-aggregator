package me.mzalietin.mdbproject.queryservice.infrastructure.rest;

import java.util.List;
import me.mzalietin.mdbproject.queryservice.domain.model.Movie;

public record ListResponse(List<Movie> result) {
}
