package com.recruitment.beerRestApiTask.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Base64;
import com.google.api.client.util.StringUtils;

import java.util.Collections;

public class TokenUtil {
    public static boolean verifyToken(String id_token, String audience) throws Exception {
        JacksonFactory jacksonFactory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), jacksonFactory)
                .setAudience(Collections.singletonList(audience)).build();
        GoogleIdToken idToken = verifier.verify(id_token);
        return idToken != null;
    }
    public static String decodeToJson(final String base64) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
    }
}
