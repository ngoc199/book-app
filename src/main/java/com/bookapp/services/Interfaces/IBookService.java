package com.bookapp.services.Interfaces;

import java.util.List;

import com.bookapp.dto.BookDTO;

public interface IBookService {
	
	List<BookDTO> findByCategoryId(int category_id);
}
