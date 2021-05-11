package com.bookapp.models;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    private String id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String bookLink;

    private int reactionNum;

    private int viewNum;

    private boolean isFree;

    @ManyToOne(targetEntity = Publisher.class)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    // @ManyToMany(targetEntity = Author.class, mappedBy = "books")
    // private Set<Author> authors;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    // @ManyToMany(targetEntity = BookCategory.class, mappedBy = "books")
    // private Set<BookCategory> categories;
    @ManyToOne
    @JoinColumn(name = "category_id")
    public BookCategory category;

    @ManyToMany
    @JoinTable(name = "user_book_favorites", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "user_book_likes", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likedUsers;

    @OneToMany(targetEntity = Review.class, mappedBy = "book")
    private Set<Review> reviews;

    @PrePersist
    private void generateId() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Book book = (Book) obj;
        return book.getId().equals(this.getId());
    }
}
