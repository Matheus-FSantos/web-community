package br.senac.sp.blog.model.security.jwt;

import br.senac.sp.blog.BlogApplication;
import br.senac.sp.blog.model.domain.User;
import br.senac.sp.blog.model.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JWTUtil {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.subscription-key}")
    private String subscriptionKey;

    @Autowired
    private UserService userService;

    public String getToken(User user) {
        Long expirationInLong = Long.valueOf(this.expiration);
        LocalDateTime expirationInLocalDateTime = LocalDateTime.now().plusMinutes(expirationInLong);
        Date expirationDate = Date.from(
                expirationInLocalDateTime.atZone(ZoneId.systemDefault()).toInstant()
        );

        return Jwts
                .builder()
                .setSubject(user.getMail())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, this.subscriptionKey)
                .compact();
    }

    private Claims getClaims(String jwtToken) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(this.subscriptionKey)
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public Boolean isValid(String jwtToken) {
        try {
            Claims claims = this.getClaims(jwtToken);
            Date expirationDate = claims.getExpiration();
            LocalDateTime expirationLocalDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(expirationLocalDateTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String getUserCredential(String jwtToken) throws ExpiredJwtException {
        return (String) this.getClaims(jwtToken).getSubject();
    }

}
