package com.bookapp.services;

import java.util.List;

import com.bookapp.models.Book;
import com.bookapp.repositories.BookRepository;
import static com.bookapp.repositories.specifications.BookSpecification.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * Get all books sort by <code>property</code>
     *
     * @param page
     * @param size
     * @param property
     * @return bookList
     */
    public Page<Book> getAllBooks(int page, int size, String property, String keyword) {
        return bookRepository.findAll(Specification.where(nameContains(keyword)),
                PageRequest.of(page, size, Sort.by(property)));
    }

    /**
     * Find books which has name containing the keyword
     *
     * @param keyword
     * @return bookList
     */
    public List<Book> findBooksByName(String keyword) {
        return bookRepository.findByNameContaining(keyword);
    }

    /**
     * Get book by id
     *
     * @param bookId
     * @return book
     * @throws Exception
     */
    public Book getBookById(String bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book does not exist"));
    }

}
