package com.bookapp.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    private String id;

    @DecimalMin(value = "0.0", message = "Rate can't be smaller than 0.0")
    @DecimalMax(value = "5.0", message = "Rate can't be greater than 5.0")
    @Digits(integer = 1, fraction = 1, message = "Rate can't have more than {integer} digit in integer and more than {fraction} digit in fraction")
    private float rate;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(targetEntity = Book.class)
    @JoinColumn(name = "book_id")
    private Book book;

    @PrePersist
    private void generateId() {
        id = UUID.randomUUID().toString().replace("-", "");
    }
}
