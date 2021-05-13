package com.bookapp.controllers;

import static com.bookapp.constants.Constants.*;
import com.bookapp.dto.authentication.AuthenticationRequest;
import com.bookapp.dto.authentication.AuthenticationResponse;
import com.bookapp.dto.authentication.FacebookResponse;
import com.bookapp.dto.favorites.GetFavoritesResponse;
import com.bookapp.dto.favorites.UpdateFavoriteRequest;
import com.bookapp.models.Book;
import com.bookapp.models.User;
import com.bookapp.security.jwt.JwtTokenProvider;
import com.bookapp.services.BookService;
import com.bookapp.services.UserService;
import com.bookapp.utils.AuthUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BookService bookService;

    /**
     * Authenticate the user who has already existed in the database
     *
     * @param request
     * @return jwt
     */
    @PostMapping()
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {

        // Create new object to call the social api
        RestTemplate restTemplate = new RestTemplate();

        // Get the facebook user id, name, and email of the user
        FacebookResponse facebookResponse = restTemplate.getForObject(
                FACEBOOK_DOMAIN + "/me?fields=id,name,email&access_token=" + authenticationRequest.getAccessToken(),
                FacebookResponse.class);

        User user;
        try {
            // If the user has already existed then log the user in
            user = userService.loadUserByFacebookId(facebookResponse.getId());
        } catch (Exception e) {
            // If the user does not exist then create new user
            User newUser = new User();
            newUser.setFacebookUserId(facebookResponse.getId());
            newUser.setName(facebookResponse.getName());
            newUser.setEmail(facebookResponse.getEmail());
            user = userService.createNewUser(newUser);
        }

        String jwt = jwtTokenProvider.generateToken(user);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * Update the favorite book of the user
     *
     * @param jwt
     * @param request
     * @return status
     */
    @PostMapping("/favorites")
    public ResponseEntity<?> updateFavorite(@RequestBody UpdateFavoriteRequest request) {
        // Get user details from the request
        User user = AuthUtils.getAuthenticatedUser();

        // Check if the book exists
        Book book;
        try {
            book = bookService.getBookById(request.getBookId());
        } catch (Exception e) {

            // Return not found status if book does not exist
            return ResponseEntity.notFound().build();
        }

        // Update the favorite book
        userService.updateFavoriteBook(user, book);

        // Return ok status if success
        return ResponseEntity.ok().build();
    }

    /**
     * Get favorite books of the user
     * @param jwt
     * @return favoriteBooks
     */
    @GetMapping("/favorites")
    public ResponseEntity<?> getFavorites() {

        // Get user details from the request
        User user = AuthUtils.getAuthenticatedUser();

        // Return favorite books
        return ResponseEntity.ok(new GetFavoritesResponse(user.getFavoriteBooks()));
    }
}
