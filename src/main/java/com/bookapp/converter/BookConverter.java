package com.bookapp.converter;

import org.springframework.stereotype.Component;

import com.bookapp.dto.AuthorDTO;
import com.bookapp.dto.BookCategoryDTO;
import com.bookapp.dto.BookDTO;
import com.bookapp.dto.PublisherDTO;
import com.bookapp.models.Author;
import com.bookapp.models.Book;
import com.bookapp.models.BookCategory;
import com.bookapp.models.Publisher;

@Component
public class BookConverter {
	
	public Book toEntity(BookDTO dto) { 
		Book entity = new Book();
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setBookLink(dto.getBookLink());
		entity.setReactionNum(dto.getReactionNum());
		entity.setViewNum(dto.getViewNum());
		entity.setFree(dto.isFree());
		
		CategoryConverter categoryConverter = new CategoryConverter();
		BookCategoryDTO categoryDTO= dto.getCategoryDTO();
		entity.setCategory(categoryConverter.toEntity(categoryDTO));
		
		AuthorConverter authorConverter = new AuthorConverter();
		AuthorDTO authorDTO= dto.getAuthorDTO();
		entity.setAuthor(authorConverter.toEntity(authorDTO));
		
		PublisherConverter publisherConverter = new PublisherConverter();
		PublisherDTO publisherDTO= dto.getPublisherDTO();
		entity.setPublisher(publisherConverter.toEntity(publisherDTO));
		
		return entity;
	}
	
	public BookDTO toDTO(Book entity) {
		BookDTO dto = new BookDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDescription(entity.getDescription());
		dto.setBookLink(entity.getBookLink());
		dto.setReactionNum(entity.getReactionNum());
		dto.setViewNum(entity.getViewNum());
		dto.setFree(entity.isFree());
		CategoryConverter categoryConverter = new CategoryConverter();
		BookCategory category = entity.getCategory();
		dto.setCategoryDTO(categoryConverter.toDTO(category));
		
		AuthorConverter authorConverter = new AuthorConverter();
		Author author= entity.getAuthor();
		dto.setAuthorDTO(authorConverter.toDTO(author));
		
		PublisherConverter publisherConverter = new PublisherConverter();
		Publisher publisher= entity.getPublisher();
		dto.setPublisherDTO(publisherConverter.toDTO(publisher));
		
		return dto;
	}
}
