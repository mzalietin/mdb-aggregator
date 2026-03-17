package me.mzalietin.imdbproject.movierating.events;

public record MovieReviewUpdated(
    Integer oldRating,
    String oldComment,
    Integer newRating,
    String newComment
) implements MovieRatingImpactEvent {

    @Override
    public Integer absoluteRatingImpact() {
        return newRating - oldRating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return 0;
    }
}
