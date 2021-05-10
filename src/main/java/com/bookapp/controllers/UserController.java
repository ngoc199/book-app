package com.bookapp.controllers;

import com.bookapp.constants.Constants;
import com.bookapp.dto.authentication.AuthenticationRequest;
import com.bookapp.dto.authentication.AuthenticationResponse;
import com.bookapp.dto.authentication.FacebookResponse;
import com.bookapp.models.User;
import com.bookapp.security.jwt.JwtTokenProvider;
import com.bookapp.services.UserService;

import org.springframework.http.ResponseEntity;
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
        FacebookResponse facebookResponse = restTemplate.getForObject(Constants.FACEBOOK_DOMAIN
                + "/me?fields=id,name,email&access_token=" + authenticationRequest.getAccessToken(),
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
}
