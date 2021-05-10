package com.bookapp.services;

import javax.transaction.Transactional;

import com.bookapp.models.Book;
import com.bookapp.models.User;
import com.bookapp.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /**
     * Get user by id
     *
     * @param userId
     * @return user
     */
    public User loadUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    /**
     * Get user by facebook id
     *
     * @param facebookId
     * @return user
     * @throws Exception
     */
    public User loadUserByFacebookId(String facebookId) throws Exception {
        return userRepository.findByFacebookUserId(facebookId).orElseThrow(() -> new Exception());
    }

    /**
     * Create new user
     *
     * @param newUser
     * @return newUser
     */
    public User createNewUser(User newUser) {
        return userRepository.save(newUser);
    }

    /**
     * Update book in user's favorites. Add the book to user's favorites if user
     * didn't like it yet. Remove the book in the user favorites if the user has
     * already liked it.
     *
     * @param user
     * @param book
     */
    @Transactional(rollbackOn = Exception.class)
    public void updateFavoriteBook(User user, Book book) {
        boolean hasBook = user.getFavoriteBooks().contains(book);
        if (hasBook) {
            user.getFavoriteBooks().remove(book);
        } else {
            user.getFavoriteBooks().add(book);
        }
        userRepository.save(user);
    }

}
