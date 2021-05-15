package com.bookapp.repositories;

import java.util.Optional;

import com.bookapp.models.Book;
import com.bookapp.models.Review;
import com.bookapp.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    public Optional<Review> findByUserAndBook(User user, Book book);
}
