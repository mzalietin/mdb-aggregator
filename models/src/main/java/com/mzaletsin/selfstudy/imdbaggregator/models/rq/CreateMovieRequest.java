package com.mzaletsin.selfstudy.imdbaggregator.models.rq;

import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieRequest {
    @Size(min = 2, max = 100)
    private String name;

    private LocalDate issueDate;
}
