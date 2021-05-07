package com.bookapp.services.Interfaces;

import java.util.List;

import com.bookapp.dto.BookCategoryDTO;

public interface ICategoryService {
	
	List<BookCategoryDTO> findAll();
	
}
