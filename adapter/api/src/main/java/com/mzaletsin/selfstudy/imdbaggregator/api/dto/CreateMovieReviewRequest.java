package com.mzaletsin.selfstudy.imdbaggregator.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieReviewRequest {
    private Integer userId;
    private String movieId;

    @Min(1)
    @Max(10)
    private Integer rating;

    @Size(max = 10_000)
    private String comment;
}
