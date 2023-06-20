package br.senac.sp.blog.model.DTO;

import br.senac.sp.blog.model.domain.User;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class UsersCredentialsDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String mail;
    private String password;

    public UsersCredentialsDTO() {

    }

    public UsersCredentialsDTO(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }

    public UsersCredentialsDTO(User user) {
        this.mail = user.getMail();
        this.password = user.getPassword();
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

    public String getPassword() {
        return password;
    }

    public void updatePassword(String password) {
        this.setPassword(password);
    }

    private void setPassword(String password) {
        this.password = password;
    }

}
