package com.bookapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookapp.controllers.Output.CategoryOutput;
import com.bookapp.services.Interfaces.ICategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private ICategoryService newService;
	
	@GetMapping(value = "/categories")
	public CategoryOutput showCategory() {
		CategoryOutput result = new CategoryOutput();
		result.setListResult(newService.findAll());
		return result;
	}
}
