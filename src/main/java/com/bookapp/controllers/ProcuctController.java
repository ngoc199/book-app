package com.bookapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookapp.controllers.Output.ProductOutput;
import com.bookapp.services.Interfaces.IProductService;

@RestController
public class ProcuctController {
	
	@Autowired
	private IProductService productService;
	@GetMapping(value = "/products")
	public ProductOutput showProduct() {
		ProductOutput result = new ProductOutput();
		result.setListResult(productService.findAll()); // test push
		return result;
	}

	@PostMapping(value = "/products/{productId}")
	public boolean purchase(@PathVariable("productId") int productId) {
		return true;
	}
}
