package com.springboot.blog.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. get Token
        String requestToken = request.getHeader("Authorization");

        //Bearer 4uhiu324h2h34h234h324h32
        System.out.println(requestToken);

        String username = null;
        String token = null;

        if(requestToken!=null && requestToken.startsWith("Bearer")){

            token = requestToken.substring(7);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
            }catch(IllegalArgumentException e){
                System.out.println("Unable to get JWT Token");
            }catch(ExpiredJwtException e){
                System.out.println("JWT token has expired");
            }catch(MalformedJwtException e){
                System.out.println("Invalid JWT token");
            }

        }else{
            System.out.println("JWT token does not begin with Bearer");
        }
        //Once we get the Token now validate and setAuthentication

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails =  this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }else{
            System.out.println("JWT username is null or context is not null");
        }
        filterChain.doFilter(request,response);
    }
}
