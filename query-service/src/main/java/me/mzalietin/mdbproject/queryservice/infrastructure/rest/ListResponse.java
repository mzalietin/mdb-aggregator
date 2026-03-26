package me.mzalietin.mdbproject.queryservice.infrastructure.rest;

import java.util.List;

public record ListResponse<T>(List<T> result) {
}
