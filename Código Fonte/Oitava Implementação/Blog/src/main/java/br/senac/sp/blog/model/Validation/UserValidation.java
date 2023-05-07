package br.senac.sp.blog.model.Validation;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.exception.BlogException;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {

    public static void validation(User user) throws BlogException {
        if(user.getName() == null)
            throw new BlogException("User", "User name is required");
        else if (user.getMail() == null)
            throw new BlogException("User", "User mail is required");
        else if(user.getPassword() == null)
            throw new BlogException("User", "User password is required");
    }

}