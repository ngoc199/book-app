package com.bookapp.converter;

import org.springframework.stereotype.Component;

import com.bookapp.dto.BookCategoryDTO;
import com.bookapp.models.BookCategory;

@Component
public class CategoryConverter {
	
	public BookCategory toEntity(BookCategoryDTO dto) { 
		BookCategory entity = new BookCategory();
		entity.setName(dto.getName());
		return entity;
	}
	
	public BookCategoryDTO toDTO(BookCategory entity) {
		BookCategoryDTO dto = new BookCategoryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
}
