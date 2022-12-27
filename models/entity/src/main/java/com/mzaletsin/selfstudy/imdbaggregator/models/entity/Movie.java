package com.mzaletsin.selfstudy.imdbaggregator.models.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Movie {
    private UUID id;
    private String name;
    private LocalDate issueDate;
    private Double rating;
}
