package model.vo;

import java.util.*;

/**
 * 
 */
public abstract class User {
    private int id;
    private String username;
    private String email;
    private Date signupDate;

    public User(String username) {
        this.username = username;
    }

    public User(int id, String username, String email, Date signupDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.signupDate = signupDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getSignupDate() {
        return signupDate;
    }

    public void setSignupDate(Date signupDate) {
        this.signupDate = signupDate;
    }


}