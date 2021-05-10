package com.bookapp.services;

import static com.bookapp.constants.Constants.*;

import java.util.HashMap;
import java.util.Map;

import com.bookapp.models.User;
import com.bookapp.repositories.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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
     * @param newUser
     * @return newUser
     */
    public User createNewUser(User newUser) {
        return userRepository.save(newUser);
    }

    /**
     * Get facebook user's data
     *
     * @param accessToken
     * @return facebookUserData
     */
    public String getFacebookEmail(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("fields", "id,name,email");
        uriVariables.put("access_token", accessToken);
        return restTemplate.getForObject(FACEBOOK_DOMAIN + "/me?fields={fields}&access_token={access_token}",
                String.class, uriVariables);
    }

}
