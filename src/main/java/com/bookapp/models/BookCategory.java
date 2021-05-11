package com.bookapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_categories")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    

//    @ManyToMany(targetEntity = Book.class)
//    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private Set<Book> books;
    
    @OneToMany(mappedBy = "category")
	private List<Book> books = new ArrayList<>();
    
    
}
