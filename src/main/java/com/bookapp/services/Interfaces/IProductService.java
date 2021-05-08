package com.bookapp.services.Interfaces;

import java.util.List;

import com.bookapp.dto.ProductDTO;

public interface IProductService {
	
	List<ProductDTO> findAll();
	boolean purchase(int productId);
}
