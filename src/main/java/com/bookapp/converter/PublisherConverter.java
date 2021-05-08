package com.bookapp.converter;

import org.springframework.stereotype.Component;

import com.bookapp.dto.PublisherDTO;
import com.bookapp.models.Publisher;

@Component
public class PublisherConverter {
	public Publisher toEntity(PublisherDTO dto) { 
		Publisher entity = new Publisher();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		return entity;
	}
	
	public PublisherDTO toDTO(Publisher entity) {
		PublisherDTO dto = new PublisherDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		return dto;
	}
}
