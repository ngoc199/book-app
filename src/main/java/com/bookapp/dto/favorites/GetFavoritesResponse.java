package com.bookapp.dto.favorites;

import java.util.Set;

import com.bookapp.models.Book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetFavoritesResponse {
    private Set<Book> favoriteBooks;
}
