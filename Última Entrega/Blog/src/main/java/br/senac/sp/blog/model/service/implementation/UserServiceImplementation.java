package br.senac.sp.blog.model.service.implementation;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.isEmpty() || username.isBlank())
            throw new UsernameNotFoundException("User not found in database!");

        User user = this.userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException("User not found in database!"));

        String[] roles =  user.isAdmin() ? new String[]{"ADMINISTRADOR", "USUARIO"} : new String[]{"USUARIO"};

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
