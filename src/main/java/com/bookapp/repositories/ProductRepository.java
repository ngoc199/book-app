package com.bookapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookapp.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
