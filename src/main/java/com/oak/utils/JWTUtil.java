package com.oak.utils;

import java.util.Date;
import java.util.UUID;

import com.oak.entities.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {

    private static String secret = "KimZ2/p5JBcTDWOjhZOytA==";;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public static User parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            User u = new User();
            u.setUsername(body.getSubject());
            u.setEmail((String) body.get("userId"));
           

            return u;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public static String generateToken(User u, String ip) {
    	
    	UUID uuid = UUID.randomUUID();
    	
        Claims claims = Jwts.claims().setSubject(u.getUsername()).setIssuedAt(new Date()).setId(uuid.toString()).setIssuer("oak");
        claims.put("userId", u.getEmail());
        
        //TODO : IP address in case of mobile devices
        claims.put("ip", "");
       
        

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
	
}