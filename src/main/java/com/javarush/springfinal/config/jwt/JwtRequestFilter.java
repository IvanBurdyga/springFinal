package com.javarush.springfinal.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@Slf4j
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenHelper jwtTokenHelper;
    private final UserDetailsService userDetailsService;

    @NullMarked
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        try {

            final String authorizationHeader = request.getHeader("Authorization");
            String username = null;
            String jwt = null;

            if (Objects.nonNull(authorizationHeader) &&
                    authorizationHeader.startsWith("Bearer ")) {
                jwt = authorizationHeader.substring(7);
                username = jwtTokenHelper.extractUsername(jwt);
            }

            if (Objects.nonNull(username) &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                boolean isTokenValidate = jwtTokenHelper.validateToken(jwt, userDetails);
                if (isTokenValidate) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (ExpiredJwtException jwtException) {
            request.setAttribute("exception", jwtException);
        } catch (BadCredentialsException |
                 UnsupportedJwtException |
                 MalformedJwtException e) {
            log.error("Filter exception: {}", e.getMessage());
            request.setAttribute("exception", e);
        }
        chain.doFilter(request, response);
    }
}
