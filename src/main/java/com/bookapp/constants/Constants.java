package com.bookapp.constants;

public final class Constants {
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_SECRET = "0042e2014827ab3c7a04c314b2d13e61f3cd5736bed88640b5efe7a725990650164f24aedcf2120a614ef4ea08afe2acaf270e3f5a94803253ffd5f91ec2b9c2";
    public static final long JWT_EXPIRATION = 1000L * 60 * 60 * 5; // 5 hours

    // Parameters' name
    public static final String USER_TOKEN_PARAM = "user_token";

    // Error Messages
    public static final String REVIEW_NOT_EXIST = "The review does not exist";
    public static final String REVIEW_NOT_BELONG_TO_USER = "The review does not belong to the user";

    // Social API domain
    public static final String FACEBOOK_DOMAIN = "https://graph.facebook.com";
    public static final String GOOGLE_DOMAIN = "https://www.googleapis.com";
}
