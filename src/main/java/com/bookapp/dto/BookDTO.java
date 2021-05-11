package com.bookapp.dto;

public class BookDTO {
    private String id;
    private String name;
    private String description;
    private String bookLink;
    private int reactionNum;
    private int viewNum;
    private boolean isFree;
    
    private PublisherDTO publisherDTO;
	private AuthorDTO authorDTO;
	private BookCategoryDTO categoryDTO;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBookLink() {
		return bookLink;
	}
	public void setBookLink(String bookLink) {
		this.bookLink = bookLink;
	}
	public int getReactionNum() {
		return reactionNum;
	}
	public void setReactionNum(int reactionNum) {
		this.reactionNum = reactionNum;
	}
	public int getViewNum() {
		return viewNum;
	}
	public void setViewNum(int viewNum) {
		this.viewNum = viewNum;
	}
	public boolean isFree() {
		return isFree;
	}
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}
	public PublisherDTO getPublisherDTO() {
		return publisherDTO;
	}
	public void setPublisherDTO(PublisherDTO publisherDTO) {
		this.publisherDTO = publisherDTO;
	}
	public AuthorDTO getAuthorDTO() {
		return authorDTO;
	}
	public void setAuthorDTO(AuthorDTO authorDTO) {
		this.authorDTO = authorDTO;
	}
	public BookCategoryDTO getCategoryDTO() {
		return categoryDTO;
	}
	public void setCategoryDTO(BookCategoryDTO categoryDTO) {
		this.categoryDTO = categoryDTO;
	}
	
}
