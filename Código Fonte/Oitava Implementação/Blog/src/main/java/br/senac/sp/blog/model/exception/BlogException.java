package br.senac.sp.blog.model.exception;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlogException extends Exception {

    private final String exceptionClass;
    private List<String> exceptionMessages = new ArrayList<>();
    private LocalDateTime exceptionAt;

    public BlogException(String exceptionClass, String exceptionMessage) {
        this.exceptionClass = exceptionClass;
        this.setExceptionMessages(exceptionMessage);
        this.setExceptionAt();
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public List<String> getExceptionMessages() {
        return exceptionMessages;
    }

    private void setExceptionMessages(String exceptionMessage) {
        this.exceptionMessages = Collections.singletonList(exceptionMessage);
    }

    public LocalDateTime getExceptionAt() {
        return exceptionAt;
    }

    private void setExceptionAt() {
        this.exceptionAt = LocalDateTime.now();
    }

    public String getExceptionAtFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.getExceptionAt().format(formatter);
    }
}
