package br.senac.sp.blog.model.validation;

import br.senac.sp.blog.model.domain.Category;
import br.senac.sp.blog.model.exception.BlogException;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidation {

    public static void validation(Category category) throws BlogException {
        List<String> messages = new ArrayList<>();

        if(category.getName() == null)
            messages.add("Category name is required");
        else if(category.getName().length() <= 3 || category.getName().length() > 255)
            messages.add("Invalid Fields");

        if(!messages.isEmpty())
            throw new BlogException("Category", messages);
    }

}
