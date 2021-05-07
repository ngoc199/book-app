package com.bookapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookapp.models.BookCategory;

public interface CategoryRepository extends JpaRepository<BookCategory, Integer>{
	
}
