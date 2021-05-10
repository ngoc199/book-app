package com.bookapp.controllers.Output;

import java.util.ArrayList;
import java.util.List;

import com.bookapp.dto.BookCategoryDTO;

public class CategoryFindAllOutput {
	private List<BookCategoryDTO> listResult = new ArrayList<>();

	public List<BookCategoryDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<BookCategoryDTO> listResult) {
		this.listResult = listResult;
	}
	
	
	
}
