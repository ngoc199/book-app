package com.bookapp.repositories.specifications;

import com.bookapp.models.Book;

import org.springframework.data.jpa.domain.Specification;

public final class BookSpecification {
    /**
     * Get the books by name
     * @param keyword
     * @return
     */
    public static Specification<Book> nameContains(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return null;
        }
        return nameContains(keyword);
    }
}
