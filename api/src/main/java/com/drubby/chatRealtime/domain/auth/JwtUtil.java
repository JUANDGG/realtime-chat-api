package com.drubby.chatRealtime.domain.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;



public abstract class JwtUtil {
    private static  String SECRET_KEY ="ed08c290d7e22f7bb324b15cbadce35b0b348564fd2d5f95752388d86d71bcca" ;

    private static String TIME_EXPIRATION = "86400000";

    private static String USER_GENERATED = "drubbyService";


    public static String createToken(CustomUserDetails customUserDetails) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        Long idUser = customUserDetails.getUserId();
        String userName = customUserDetails.getUsername();
        String email = customUserDetails.getEmail();
        String authorities =customUserDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return JWT.create()
                .withJWTId(idUser.toString())
                .withIssuer(USER_GENERATED)
                .withSubject(userName)
                .withClaim("authorities", authorities)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(TIME_EXPIRATION)))
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public static DecodedJWT validateAndDecodifiedToken (String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier =  JWT.require(algorithm).withIssuer(USER_GENERATED).build();

            return verifier.verify(token) ;

        } catch (JWTVerificationException exception) {
            //handled error
            throw new JWTVerificationException("token no valido");
        }
    }



    public static Claim getClaim (DecodedJWT decodedJWT , String claimName){
        return decodedJWT.getClaim(claimName);
    }



    public static Map<String , Claim> getAllClaim (DecodedJWT decodedJWT){
        return decodedJWT.getClaims();

    }
}
