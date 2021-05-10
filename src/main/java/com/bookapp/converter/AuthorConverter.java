package com.bookapp.converter;

import org.springframework.stereotype.Component;

import com.bookapp.dto.AuthorDTO;
import com.bookapp.models.Author;

@Component
public class AuthorConverter {
	public Author toEntity(AuthorDTO dto) { 
		Author entity = new Author();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	
	public AuthorDTO toDTO(Author entity) {
		AuthorDTO dto = new AuthorDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
