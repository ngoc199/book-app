package com.bookapp.converter;

import org.springframework.stereotype.Component;

import com.bookapp.dto.ProductDTO;
import com.bookapp.models.Product;

@Component
public class ProductConverter {
	public Product toEntity(ProductDTO dto) { 
		Product entity = new Product();
		entity.setPeriod(dto.getPeriod());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		return entity;
	}
	
	public ProductDTO toDTO(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setPeriod(entity.getPeriod());
		dto.setDescription(entity.getDescription());
		dto.setPrice(entity.getPrice());
		return dto;
	}
}
