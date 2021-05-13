package com.bookapp.security.jwt;

import java.util.Date;

import static com.bookapp.constants.Constants.*;
import com.bookapp.models.User;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    /**
     * Generate new jwt token
     * @param user
     * @return jwtToken
     */
    public String generateToken(User user) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder().setSubject(user.getId()).setIssuedAt(now).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET).claim("user-role", user.getRole()).compact();
    }

    /**
     * Get user id from jwt
     * @param token
     * @return userId
     */
    public String getUserIdFromJwt(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * Get user role from jwt
     * @param token
     * @return userRole
     */
    public String getUserRoleFromJwt(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.get("user-role").toString();
    }

    /**
     * Check if the jwt is valid
     * @param token
     * @return checkResult
     */
    public boolean isTokenValid(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims != null;
    }

    /**
     * Get claims from jwt
     * @param token
     * @return claims
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims;
    }

}
