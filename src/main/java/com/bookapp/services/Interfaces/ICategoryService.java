package com.bookapp.services.Interfaces;

import java.util.List;

import com.bookapp.dto.BookCategoryDTO;

public interface ICategoryService {
	
	List<BookCategoryDTO> findAll();
	List<BookCategoryDTO> findByNameContaining(String keyword);
	BookCategoryDTO findById(int categoryId);
}
