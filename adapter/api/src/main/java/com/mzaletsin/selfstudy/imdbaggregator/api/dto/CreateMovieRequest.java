package com.mzaletsin.selfstudy.imdbaggregator.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMovieRequest {
    private String name;
    private java.time.LocalDate issueDate;
}
