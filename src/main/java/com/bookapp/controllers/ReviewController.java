package com.bookapp.controllers;

import javax.validation.Valid;

import com.bookapp.constants.Constants;
import com.bookapp.dto.reviews.AddBookReviewRequest;
import com.bookapp.models.Review;
import com.bookapp.models.User;
import com.bookapp.services.ReviewService;
import com.bookapp.utils.AuthUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * Update the review of the user
     *
     * @param reviewId
     * @param request
     * @return status
     */
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable("reviewId") String reviewId,
            @RequestBody @Valid AddBookReviewRequest request) {
        Review review;
        try {
            review = reviewService.getReviewById(reviewId);
        } catch (Exception e) {

            // Response bad request if the review does not exist
            return ResponseEntity.badRequest().body(Constants.REVIEW_NOT_EXIST);
        }

        // Get user's details from security context
        User user = AuthUtils.getAuthenticatedUser();

        // Response bad request if the review does not belong to the user
        if (review.getUser().equals(user)) {
            return ResponseEntity.badRequest().body(Constants.REVIEW_NOT_BELONG_TO_USER);
        }

        // Update the review
        review.setRate(request.getRate());
        review.setContent(request.getContent());
        reviewService.saveReview(review);

        // Response ok if success
        return ResponseEntity.ok().build();
    }

    /**
     * Delete review by id
     *
     * @param reviewId
     * @return status
     */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable("reviewId") String reviewId) {
        Review review;
        try {
            review = reviewService.getReviewById(reviewId);
        } catch (Exception e) {

            // Response bad request if the review does not exist
            return ResponseEntity.badRequest().body(Constants.REVIEW_NOT_EXIST);
        }

        // Get user's details from security context
        User user = AuthUtils.getAuthenticatedUser();

        // Response bad request if the review does not belong to the user
        if (review.getUser().equals(user)) {
            return ResponseEntity.badRequest().body(Constants.REVIEW_NOT_BELONG_TO_USER);
        }

        // Delete the review
        reviewService.deleteReviewById(reviewId);

        // Response ok if success
        return ResponseEntity.ok().build();
    }

}
