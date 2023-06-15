package br.senac.sp.blog.model.validation;

import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.exception.BlogException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {

    public static void validation(User user) throws BlogException {
        List<String> messages = new ArrayList<>();

        if(user.getName() == null)
            messages.add("User name is required");
        else if(user.getName().length() <= 3 || user.getName().length() > 45)
                messages.add("Invalid Fields");

        if (user.getMail() == null)
            messages.add("User mail is required");
        else if((user.getMail().length() <= 3 || user.getMail().length() > 160) || !user.getMail().contains("@"))
                if(!messages.contains("Invalid Fields"))
                    messages.add("Invalid Fields");

        if(user.getPassword() == null)
            messages.add("User password is required");
        else if(user.getPassword().length() <= 3 || user.getPassword().length() > 30)
            if(!messages.contains("Invalid Fields"))
                messages.add("Invalid Fields");

        if(!messages.isEmpty())
            throw new BlogException("User", messages);
    }

}