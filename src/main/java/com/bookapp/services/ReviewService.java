package com.bookapp.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.bookapp.models.Book;
import com.bookapp.models.Review;
import com.bookapp.models.User;
import com.bookapp.repositories.ReviewRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    /**
     * Get review by user and book
     *
     * @param user
     * @param book
     * @return
     * @throws Exception
     */
    public Review findReviewByUserAndBook(User user, Book book) {
        Optional<Review> review = reviewRepository.findByUserAndBook(user, book);
        if (review.isPresent()) {
            return review.get();
        }
        return null;
    }

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
