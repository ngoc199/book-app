package com.bookapp.services;

import javax.transaction.Transactional;

import com.bookapp.models.Review;
import com.bookapp.repositories.ReviewRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    /**
     * Save review
     *
     * @param review
     * @return review
     */
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    /**
     * Get the review by id
     *
     * @param reviewId
     * @return review
     * @throws Exception
     */
    public Review getReviewById(String reviewId) throws Exception {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new Exception());
    }

    /**
     * Delete the review by id
     *
     * @param reviewId
     */
    @Transactional
    public void deleteReviewById(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
