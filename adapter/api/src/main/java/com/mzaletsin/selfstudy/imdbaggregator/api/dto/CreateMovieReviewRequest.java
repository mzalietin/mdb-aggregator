package com.mzaletsin.selfstudy.imdbaggregator.api.dto;

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
    private Integer rating;
    private String comment;
}
