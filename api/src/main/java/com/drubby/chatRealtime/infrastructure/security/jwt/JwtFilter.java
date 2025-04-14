package com.drubby.chatRealtime.infrastructure.security.jwt;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import java.util.Collection;


@Service
public class JwtFilter implements WebFilter {


    /*
    *  @Override
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
    * */


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null ) {
            String token = authHeader.substring(7);
            DecodedJWT decodedJWT = JwtUtil.validateAndDecodifiedToken(token);

            String username = JwtUtil.getClaim(decodedJWT, "name").asString();
            String authorities = JwtUtil.getClaim(decodedJWT, "authorities").asString();

            Collection grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);

            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }

        return chain.filter(exchange);
    }
}
