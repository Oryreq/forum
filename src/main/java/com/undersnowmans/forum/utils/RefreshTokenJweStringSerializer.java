package com.undersnowmans.forum.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jwt.EncryptedJWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.undersnowmans.forum.jwt.Token;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.function.Function;

@AllArgsConstructor
@RequiredArgsConstructor
@Slf4j
@Setter
public class RefreshTokenJweStringSerializer implements Function<Token, String> {

    private final JWEEncrypter jweEncrypter;

    private JWEAlgorithm jweAlgorithm = JWEAlgorithm.DIR;

    private EncryptionMethod encryptionMethod = EncryptionMethod.A128GCM;

    @Override
    public String apply(Token token) {
        var jwsHeader = new JWEHeader.Builder(this.jweAlgorithm, this.encryptionMethod)
                .keyID(token.id().toString())
                .build();

        var claimsSet = new JWTClaimsSet.Builder()
                .jwtID(token.id().toString())
                .subject(token.username())
                .issueTime(Date.from(token.createdAt()))
                .expirationTime(Date.from(token.expiresAt()))
                .claim("authorities", token.authorities())
                .build();

        var encryptedJwt = new EncryptedJWT(jwsHeader, claimsSet);
        try {
            encryptedJwt.encrypt(this.jweEncrypter);
            return encryptedJwt.serialize();
        } catch (JOSEException e) {
            log.error(e.getMessage());
        }

        return null;
    }
}
