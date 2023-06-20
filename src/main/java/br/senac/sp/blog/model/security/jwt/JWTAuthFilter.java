package br.senac.sp.blog.model.security.jwt;

import br.senac.sp.blog.model.service.UserService;
import br.senac.sp.blog.model.service.implementation.UserServiceImplementation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthFilter extends OncePerRequestFilter {

    private JWTUtil jwtUtil;

    private UserServiceImplementation userServiceImplementation;

    public JWTAuthFilter(JWTUtil jwtUtil, UserServiceImplementation userServiceImplementation) {
        this.jwtUtil = jwtUtil;
        this.userServiceImplementation = userServiceImplementation;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization != null && authorization.startsWith("Bearer")) {
            String jwtToken = authorization.split(" ")[1];
            if(this.jwtUtil.isValid(jwtToken)){
                UserDetails userDetails = this.userServiceImplementation.loadUserByUsername(this.jwtUtil.getUserCredential(jwtToken));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

}
