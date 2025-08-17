package com.forohub.dto.auth;

public record TokenResponse(String accessToken, String tokenType) {
    public TokenResponse(String accessToken) {
        this(accessToken, "Bearer");
    }
}
