package com.ptit.touristservice.auth.model;

import com.ptit.touristservice.constants.Constant;
import com.ptit.touristservice.utils.DateTimeUltils;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "email_verification_token")
public class EmailVerificationToken {
    public static final String USER = "user";
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String token;
    private Date expirationDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userID")
    private User user;

    public EmailVerificationToken(User user) {
        this.user = user;
        this.expirationDate = DateTimeUltils.calculateExpirationDate(Constant.EMAIL_VERIFICATION_TOKEN_EXPIRED_DAY);
    }

    public EmailVerificationToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
