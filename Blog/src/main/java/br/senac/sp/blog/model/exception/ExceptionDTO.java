package br.senac.sp.blog.model.exception;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExceptionDTO {
    private String exceptionClass;
    private List<String> exceptionMessages;
    private String exceptionAt;

    public ExceptionDTO() {}

    public ExceptionDTO(String exceptionClass, List<String> exceptionMessages, String exceptionAt) {
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
