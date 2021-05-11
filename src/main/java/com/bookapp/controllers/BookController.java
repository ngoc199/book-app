package com.bookapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import com.bookapp.controllers.Output.BookOutput;
import com.bookapp.dto.reviews.AddBookReviewRequest;
import com.bookapp.dto.reviews.GetBookReviewsResponse;
import com.bookapp.models.Book;
import com.bookapp.models.Review;
import com.bookapp.models.User;
import com.bookapp.services.BookService;
import com.bookapp.services.ReviewService;
import com.bookapp.services.Interfaces.IBookService;
import com.bookapp.utils.ClassUtils;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
@ResponseBody
public class BookController {

    @Autowired
    private final IBookService iBookService;
    private final BookService bookService;
    private final ReviewService reviewService;

    /**
     * Get all books and sort by the <code>property</code>. If the name parameter is
     * defined, it will find the books which have the name contains the specified
     * keyword
     *
     * @param page
     * @param size
     * @param property
     * @return bookList
     */
    @GetMapping()
    public Page<Book> getAllBooks(@RequestParam(name = "page", defaultValue = "10") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortBy", defaultValue = "name") String property,
            @RequestParam(name = "name", required = false) String bookName) {
        if (ClassUtils.isClassContainsProperty(Book.class, property)) {
            property = "name";
        }
        return bookService.getAllBooks(page, size, property, bookName);
    }

    /**
     * Get book by id
     *
     * @param bookId
     * @return book
     */
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable("bookId") String bookId) {
        try {
            return bookService.getBookById(bookId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get reviews of the book
     *
     * @param bookId
     * @return bookReviews
     */
    @GetMapping("/{bookId}/reviews")
    public ResponseEntity<?> getBookReviews(@PathVariable("bookId") String bookId) {
        Book book;
        try {
            book = bookService.getBookById(bookId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new GetBookReviewsResponse(book.getReviews()));
    }

    /**
     * Add new review to the book
     *
     * @param bookId
     * @return status
     */
    @PostMapping("/{bookId}/reviews")
    public ResponseEntity<?> createBookReviews(@PathVariable("bookId") String bookId,
            @RequestBody @Valid AddBookReviewRequest request) {

        // Get user's details
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();

        // Check if the book exists
        Book book;
        try {
            book = bookService.getBookById(bookId);
        } catch (Exception e) {
            // Response bad request if can't find book
            return ResponseEntity.badRequest().build();
        }

        // Add new review to the book
        Review review = new Review();
        review.setUser(user);
        review.setBook(book);
        review.setRate(request.getRate());
        review.setContent(request.getContent());
        reviewService.saveReview(review);

        // Response ok if success
        return ResponseEntity.ok().build();
    }

	@GetMapping(value = "/categories/{category_id}")
	public BookOutput showBook(@PathVariable("category_id") int category_id) {
		BookOutput result = new BookOutput();
		result.setListResult(iBookService.findByCategoryId(category_id));
		return result;
	}
}
