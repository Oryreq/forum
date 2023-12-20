package com.undersnowmans.forum.jwt;

public record Tokens(String accessToken, String accessTokenExpiry, String refreshToken, String refreshTokenExpiry){};
