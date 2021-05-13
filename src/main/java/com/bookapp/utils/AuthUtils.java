package com.bookapp.utils;

import javax.annotation.PostConstruct;

import com.bookapp.models.User;
import com.bookapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    private static UserService userService;

    @Autowired
    private UserService uService;

    @PostConstruct
    private void initUserService() {
        userService = this.uService;
    }

    /**
     * Get the current authenticated user in the request
     *
     * @return user
     */
    public static User getAuthenticatedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.loadUserById(user.getId());
    }
}
