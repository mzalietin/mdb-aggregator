package me.mzalietin.mdbproject.movie.infrastructure.rest.dto.rq;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record MovieIds(@NotEmpty @Size(max = 100) List<String> movieIds) {
}
