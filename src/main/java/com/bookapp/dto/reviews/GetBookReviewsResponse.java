package com.bookapp.dto.reviews;

import java.util.Set;

import com.bookapp.models.Review;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GetBookReviewsResponse {
    private Set<Review> reviews;
}
