package com.bookapp.controllers.Output;

import java.util.ArrayList;
import java.util.List;

import com.bookapp.dto.BookCategoryDTO;
import com.bookapp.dto.BookDTO;

public class CategoryFindByIdOutput {
	private BookCategoryDTO bookCategoryDTO = new BookCategoryDTO();
	private List<BookDTO> bookDTOs = new ArrayList<>();
	
	public BookCategoryDTO getBookCategoryDTO() {
		return bookCategoryDTO;
	}
	public void setBookCategoryDTO(BookCategoryDTO bookCategoryDTO) {
		this.bookCategoryDTO = bookCategoryDTO;
	}
	public List<BookDTO> getBookDTOs() {
		return bookDTOs;
	}
	public void setBookDTOs(List<BookDTO> bookDTOs) {
		this.bookDTOs = bookDTOs;
	}
	
}
