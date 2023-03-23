/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.configuration;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Filter to get and validate access token.
 */

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenConfig jwtTokenConfig;

    @Value("${jwt.secret}")
    private String secret;

    private final String HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if(jwtExists(request)) {
            String token = getAccessToken(request);

            if (jwtTokenConfig.validateAccessToken(token)) {
                Claims claims = validateToken(token);
                setUpSpringAuthentication(claims);
                filterChain.doFilter(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Obtiene el token de acceso desde el header del request.
     * @param request Petición del usuario, debe incluir header Authorization.
     * @return Regresa únicamente el token, sin la palabra Bearer
     */
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER);
        String token = header.split(" ")[1].trim();
        return token;
    }

    /**
     * Válida el token ingresado en el request.
     * @param token token ingresado en el header del request.
     * @return Regresa si el token es válido o no.
     */
    private Claims validateToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Válida si se ha ingresado un token en el header del request.
     * @param request Petición por parte del usuario.
     * @return Regresa si hay un token.
     */
    private boolean jwtExists(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HEADER);
        if (authenticationHeader == null)
            return false;
        return true;
    }

    /**
     * Genera la autenticación del usuario y agrega sus roles.
     * @param claims Información adicional del usuario (roles).
     */
    private void setUpSpringAuthentication(Claims claims) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(claims.get("authorities").toString());

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
                grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

}
