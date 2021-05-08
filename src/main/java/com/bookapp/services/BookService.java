package com.bookapp.services;

import static com.bookapp.repositories.specifications.BookSpecification.nameContains;

import java.util.ArrayList;
import java.util.List;kkk

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.bookapp.converter.BookConverter;
import com.bookapp.dto.BookDTO;
import com.bookapp.models.Book;
import com.bookapp.repositories.BookRepository;
import com.bookapp.services.Interfaces.IBookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
	@Autowired
    private final BookRepository bookRepository;
    @Autowired
	private BookConverter bookConverter;

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

	@Override
	public List<BookDTO> findByCategoryId(int category_id) {
		// TODO Auto-generated method stub
		List<BookDTO> results = new ArrayList<>();
		List<Book> entities = bookRepository.findByCategoryId(category_id);
		BookDTO bookDTO = new BookDTO();
		for(Book item: entities) {
			bookDTO = bookConverter.toDTO(item);
			results.add(bookDTO);
		}
		return results;
	}
}
