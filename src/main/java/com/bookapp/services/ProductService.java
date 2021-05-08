package com.bookapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookapp.converter.ProductConverter;
import com.bookapp.dto.ProductDTO;
import com.bookapp.models.Product;
import com.bookapp.repositories.ProductRepository;
import com.bookapp.services.Interfaces.IProductService;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductConverter productConverter;

	@Override
	public List<ProductDTO> findAll() {
		// TODO Auto-generated method stub
		List<ProductDTO> results = new ArrayList<>();
		List<Product> entities = productRepository.findAll();
		for(Product item: entities) {
			ProductDTO productDTO = productConverter.toDTO(item);
			results.add(productDTO);
		}
		return results;
	}

	@Override
	public boolean purchase(int productId) {
		return true;
	}
}
