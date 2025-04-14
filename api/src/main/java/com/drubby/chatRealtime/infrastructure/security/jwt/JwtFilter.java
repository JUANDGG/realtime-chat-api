package com.drubby.chatRealtime.infrastructure.security.jwt;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collection;


@Service
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenRequest = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(tokenRequest != null){
            tokenRequest = tokenRequest.substring(7);

            //validate token
            DecodedJWT decodedJWT = JwtUtil.validateAndDecodifiedToken(tokenRequest);

            //extract user details
            String userNameEmail = JwtUtil.getClaim(decodedJWT, "name").asString();
            String authorities = JwtUtil.getClaim(decodedJWT, "authorities").asString();
            Collection<? extends GrantedAuthority> grantedAuthoritieList  = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);


            //register object the authentication
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userNameEmail,null,grantedAuthoritieList);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);

        }

        filterChain.doFilter(request, response);

    }
}
