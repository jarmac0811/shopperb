package com.recruitment.beerRestApiTask.security;

public class JwtProperties {
    public static final String SECRET = "secretKey";
    public static final int EXPIRATION_TIME = 864000000; //10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_AUTH_TYPE = "AuthType";
    public static final String HEADER_ID_TOKEN = "IdToken";
    public static final String TOKEN_AUD_VALUE = "183195594966-ltk1u1um0gjsa1357og9v1f7uplhlr64.apps.googleusercontent.com";
    public static final String GOOGLE_AUTH_TYPE = "googleAuth";
    public static final String FORM_AUTH_TYPE = "formLogin";
    public static final String RS256_ALG_TYPE = "RS256";
    public static final String HMAC512_ALG_TYPE = "HS512";
}
