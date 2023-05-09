package br.senac.sp.blog.model.exception;

import org.springframework.stereotype.Component;

import java.util.List;

public class ApiErrors {
    private final String exceptionClass;
    private final List<String> exceptionMessages;
    private final String exceptionAt;

    public ApiErrors(String exceptionClass, List<String> exceptionMessages, String exceptionAt) {
        this.exceptionClass = exceptionClass;
        this.exceptionMessages = exceptionMessages;
        this.exceptionAt = exceptionAt;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public List<String> getExceptionMessages() {
        return exceptionMessages;
    }

    public String getExceptionAt() {
        return exceptionAt;
    }

}
