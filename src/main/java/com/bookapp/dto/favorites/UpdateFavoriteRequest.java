package com.bookapp.dto.favorites;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UpdateFavoriteRequest {
    private String bookId;
}
