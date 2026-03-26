package me.mzalietin.mdbproject.queryservice.domain.model;

import java.io.Serializable;

public record MovieWithUserRating(Integer userRating, Movie movie) {
}
