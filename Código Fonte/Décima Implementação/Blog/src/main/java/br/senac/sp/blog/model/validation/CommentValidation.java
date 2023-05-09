package br.senac.sp.blog.model.validation;

import br.senac.sp.blog.model.domain.Comment;
import br.senac.sp.blog.model.exception.BlogException;

import java.util.ArrayList;
import java.util.List;

public class CommentValidation {

    public static void validation(Comment comment) throws BlogException {
        List<String> messages = new ArrayList<>();

        if(comment.getText() == null)
            messages.add("Comment text is required");
        else if(comment.getText().length() <= 3 || comment.getText().length() > 255)
            messages.add("Invalid Fields");

        if(comment.getAuthor() == null)
            messages.add("Comment author is required");

        if(comment.getPost() == null)
            messages.add("Comment post is required");

        if(!messages.isEmpty())
            throw new BlogException("Comment", messages);
    }

}
