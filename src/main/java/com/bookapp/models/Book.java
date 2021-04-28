package com.bookapp.models;

import java.sql.Blob;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @Lob
    private Blob bookFile;

    private int reactionNum;

    private int viewNum;

    private boolean isFree;

    @ManyToOne(targetEntity = Publisher.class)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany(targetEntity = Author.class, mappedBy = "books")
    private Set<Author> authors;

    @ManyToMany(targetEntity = BookCategory.class, mappedBy = "books")
    private Set<BookCategory> categories;

    @PrePersist
    private void generateId() {
        id = UUID.randomUUID().toString().replace("-", "");
    }

}
