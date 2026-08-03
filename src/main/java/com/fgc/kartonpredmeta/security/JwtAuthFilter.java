package com.fgc.kartonpredmeta.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try{
                DecodedJWT decodedJWT=jwtService.validateTokenAndGetDecoded(token);

                String username=decodedJWT.getSubject();
                String uloga=decodedJWT.getClaim("uloga").asString();

                SimpleGrantedAuthority authority=new SimpleGrantedAuthority(uloga);

                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(username,null, List.of(authority));

                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch(Exception e){
                System.out.println("Token validation failed: " + e.getMessage());
            }
        }

        filterChain.doFilter(request,response);
    }
}
