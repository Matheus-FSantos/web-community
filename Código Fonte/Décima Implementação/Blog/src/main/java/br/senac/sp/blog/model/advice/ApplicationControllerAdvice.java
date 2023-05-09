package br.senac.sp.blog.model.advice;

import br.senac.sp.blog.model.exception.ApiErrors;
import br.senac.sp.blog.model.exception.BlogException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BlogException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleBusinessRuleException(BlogException blogException) {
        return new ApiErrors(blogException.getExceptionClass(), blogException.getExceptionMessages(), blogException.getExceptionAtFormatted());
    }

}
