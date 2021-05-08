package com.bookapp.converter;

import org.springframework.stereotype.Component;

import com.bookapp.dto.BookDTO;
import com.bookapp.models.Book;

@Component
public class BookConverter {
	public Book toEntity(BookDTO dto) { 
		Book entity = new Book();kkk
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setBookLink(dto.getBookLink());
		entity.setReactionNum(dto.getReactionNum());
		entity.setViewNum(dto.getViewNum());
		entity.setFree(dto.isFree());
		return entity;
	}
	
	public BookDTO toDTO(Book entity) {
		BookDTO dto = new BookDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setBookLink(entity.getBookLink());
		dto.setReactionNum(entity.getReactionNum());
		dto.setViewNum(entity.getViewNum());
		dto.setFree(entity.isFree());
		return dto;
	}
}
