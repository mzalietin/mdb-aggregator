package me.mzalietin.imdbproject.movierating.events.in;

public record MovieReviewUpdated(
    Integer oldRating,
    String oldComment,
    Integer newRating,
    String newComment
) implements MovieRatingImpact {

    @Override
    public Integer absoluteRatingImpact() {
        return newRating - oldRating;
    }

    @Override
    public Integer reviewsCountImpact() {
        return 0;
    }
}
