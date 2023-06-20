package br.senac.sp.blog.controller.advice;

import br.senac.sp.blog.model.exception.ExceptionDTO;
import br.senac.sp.blog.model.exception.BlogException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(BlogException.class)
    public ResponseEntity<ExceptionDTO> handleBusinessRuleException(BlogException blogException) {
        return ResponseEntity.badRequest().body(new ExceptionDTO(blogException.getExceptionClass(), blogException.getExceptionMessages(), blogException.getExceptionAtFormatted()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        return ResponseEntity.badRequest().body(new ExceptionDTO("UsernameNotFoundException.class", Collections.singletonList(usernameNotFoundException.getMessage()), LocalDateTime.now().toString()));
    }

}
