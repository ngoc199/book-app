package com.bookapp.controllers.Output;

import java.util.ArrayList;
import java.util.List;

import com.bookapp.dto.BookDTO;

public class BookOutput {
	private List<BookDTO> listResult = new ArrayList<>();

	public List<BookDTO> getListResult() {
		return listResult;
	}
	
	public void setListResult(List<BookDTO> listResult) {
		this.listResult = listResult;
	}
	
}
