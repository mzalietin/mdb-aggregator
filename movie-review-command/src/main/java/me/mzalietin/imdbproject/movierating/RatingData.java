package me.mzalietin.imdbproject.movierating;

public class RatingData {
    Integer absoluteRating;
    Integer reviewsCount;

    public RatingData() {
    }

    public RatingData(final Integer absoluteRating, final Integer reviewsCount) {
        this.absoluteRating = absoluteRating;
        this.reviewsCount = reviewsCount;
    }

    public Integer getAbsoluteRating() {
        return absoluteRating;
    }

    public void setAbsoluteRating(final Integer absoluteRating) {
        this.absoluteRating = absoluteRating;
    }

    public Integer getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(final Integer reviewsCount) {
        this.reviewsCount = reviewsCount;
    }
}
