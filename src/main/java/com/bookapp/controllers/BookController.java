package com.bookapp.controllers;

import com.bookapp.models.Book;
import com.bookapp.services.BookService;
import com.bookapp.utils.ClassUtils;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
@ResponseBody
public class BookController {

    private final BookService bookService;

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

}
