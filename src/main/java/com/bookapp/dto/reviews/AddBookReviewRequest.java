package com.bookapp.dto.reviews;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddBookReviewRequest {
    @DecimalMin(value = "0.0", message = "Rate can't be smaller than 0.0")
    @DecimalMax(value = "5.0", message = "Rate can't be greater than 5.0")
    @Digits(integer = 1, fraction = 1, message = "Rate can't have more than {integer} digit in integer and more than {fraction} digit in fraction")
    private float rate;
    private String content;
}
