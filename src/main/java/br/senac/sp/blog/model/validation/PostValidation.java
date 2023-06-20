package br.senac.sp.blog.model.validation;

import br.senac.sp.blog.model.domain.Post;
import br.senac.sp.blog.model.exception.BlogException;

import java.util.ArrayList;
import java.util.List;

public class PostValidation {

    public static void validation(Post post) throws BlogException{
        List<String> messages = new ArrayList<>();

        if(post.getTitle() == null)
            messages.add("Post title is required");
        else if(post.getTitle().length() <= 3 || post.getTitle().length() > 75)
            messages.add("Invalid Fields");

        if(post.getText() == null)
            messages.add("Post message is required");
        else if(post.getText().length() <= 3 || post.getText().length() > 255)
            if(!messages.contains("Invalid Fields"))
                messages.add("Invalid Fields");

        if(post.getAuthor() == null)
            messages.add("Post author is required");

        if(post.getCategory() == null)
            messages.add("Post category is required");

        if(!messages.isEmpty())
            throw new BlogException("Post", messages);
    }

}
