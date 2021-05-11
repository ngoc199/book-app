package com.bookapp.dto;

import java.util.ArrayList;
import java.util.List;

public class BookCategoryDTO {
	private int id;
	private String name;
	private List<BookDTO> listBookDTO = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public List<BookDTO> getListBookDTO() {
//		return listBookDTO;
//	}
//
//	public void setListBookDTO(List<BookDTO> listBookDTO) {
//		this.listBookDTO = listBookDTO;
//	}
	
}
