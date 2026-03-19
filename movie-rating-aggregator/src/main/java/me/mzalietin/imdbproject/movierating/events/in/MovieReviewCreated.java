package me.mzalietin.imdbproject.movierating.events.in;

public record MovieReviewCreated(
    Integer rating,
    String comment
) implements MovieRatingImpact {

    @Override
    public Integer absoluteRatingImpact() {
        return rating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return 1;
    }
}
