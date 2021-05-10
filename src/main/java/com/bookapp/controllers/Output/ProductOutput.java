package com.bookapp.controllers.Output;

import java.util.ArrayList;
import java.util.List;

import com.bookapp.dto.ProductDTO;

public class ProductOutput {
	private List<ProductDTO> listResult = new ArrayList<>();
	
	public List<ProductDTO> getListResult() {
		return listResult;
	}
	public void setListResult(List<ProductDTO> listResult) {
		this.listResult = listResult;
	}
	
	
	
}
