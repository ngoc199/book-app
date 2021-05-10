package com.bookapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookapp.converter.CategoryConverter;
import com.bookapp.dto.BookCategoryDTO;
import com.bookapp.models.BookCategory;
import com.bookapp.repositories.CategoryRepository;
import com.bookapp.services.Interfaces.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;

	@Override
	public List<BookCategoryDTO> findAll() {
		// TODO Auto-generated method stub
		List<BookCategoryDTO> results = new ArrayList<>();
		List<BookCategory> entities = categoryRepository.findAll();
		for(BookCategory item: entities) {
			BookCategoryDTO newDTO = categoryConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}

	@Override
	public List<BookCategoryDTO> findByNameContaining(String keyword) {
		// TODO Auto-generated method stub
		List<BookCategoryDTO> results = new ArrayList<>();
		List<BookCategory> entities = categoryRepository.findByNameContaining(keyword);
		for(BookCategory item: entities) {
			BookCategoryDTO newDTO = categoryConverter.toDTO(item);
			results.add(newDTO);
		}
		return results;
	}

	@Override
	public BookCategoryDTO findById(int categoryId) {
		// TODO Auto-generated method stub
		BookCategory entity = categoryRepository.findById(categoryId);
		return categoryConverter.toDTO(entity);
	}
}
