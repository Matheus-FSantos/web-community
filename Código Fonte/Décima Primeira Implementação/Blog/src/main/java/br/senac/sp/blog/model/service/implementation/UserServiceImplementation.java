package br.senac.sp.blog.model.service.implementation;

import br.senac.sp.blog.model.exception.BlogException;
import br.senac.sp.blog.model.repository.UserRepository;
import br.senac.sp.blog.model.security.implementation.PasswordEncoderImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import br.senac.sp.blog.model.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserDetailsService {

    @Autowired
    private PasswordEncoderImplementation passwordEncoderImplementation;

    @Autowired
    private UserRepository userRepository;

    public UserDetails authenticate(User user) throws BlogException {
        UserDetails userDetails = this.loadUserByUsername(user.getMail());

        if(passwordEncoderImplementation.getPasswordEncoder().matches(user.getPassword(), userDetails.getPassword()))
            return userDetails;

        throw new BlogException("User.class", "User e-mail, or password, is invalid!");
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        if(mail == null)
            throw new UsernameNotFoundException("User mail is required!");

        if(mail.isEmpty() || mail.isBlank())
            throw new UsernameNotFoundException("User not found in database!");

        User user = this.userRepository.findByMail(mail).orElseThrow(() -> new UsernameNotFoundException("User not found in database!"));

        String[] roles =  user.isAdmin() ? new String[]{"ADMINISTRADOR", "USUARIO"} : new String[]{"USUARIO"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
