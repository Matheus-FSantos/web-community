package br.senac.sp.blog.model.DTO;

import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class JWTTokenDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String mail;

    private String jwtToken;

    public JWTTokenDTO() {

    }

    public JWTTokenDTO(String mail, String jwtToken) {
        this.mail = mail;
        this.jwtToken = jwtToken;
    }

    public String getMail() {
        return mail;
    }

    public void updateMail(String mail) {
        this.setMail(mail);
    }

    private void setMail(String mail) {
        this.mail = mail;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void updateJwtToken(String jwtToken) {
        this.setJwtToken(jwtToken);
    }

    private void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
