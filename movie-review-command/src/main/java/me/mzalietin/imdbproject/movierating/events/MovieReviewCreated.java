package me.mzalietin.imdbproject.movierating.events;

public record MovieReviewCreated(
    Integer rating,
    String comment
) implements MovieRatingImpactEvent {

    @Override
    public Integer absoluteRatingImpact() {
        return rating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return 1;
    }
}
