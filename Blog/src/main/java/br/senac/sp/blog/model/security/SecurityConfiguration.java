package br.senac.sp.blog.model.security;

import br.senac.sp.blog.model.security.implementation.PasswordEncoderImplementation;
import br.senac.sp.blog.model.security.jwt.JWTAuthFilter;
import br.senac.sp.blog.model.security.jwt.JWTUtil;
import br.senac.sp.blog.model.service.UserService;
import br.senac.sp.blog.model.service.implementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private PasswordEncoderImplementation passwordEncoderImplementation;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JWTAuthFilter(this.jwtUtil, this.userServiceImplementation);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authentication) throws Exception {
        authentication
                .userDetailsService(this.userServiceImplementation)
                .passwordEncoder(this.passwordEncoderImplementation.getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/categories/**", "/api/posts/**", "/api/comments/**", "/api/ranking/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/categories/**").hasRole("ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, "/api/posts/**", "/api/comments/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/posts/**", "/api/comments/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/posts/**", "/api/comments/**", "/api/users/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/api/categories/**").hasRole("ADMINISTRADOR")
                .antMatchers("/swagger-ui/**").hasRole("ADMINISTRADOR")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(this.jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
