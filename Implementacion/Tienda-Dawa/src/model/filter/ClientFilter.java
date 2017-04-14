package model.filter;

import model.vo.EClientType;

import java.util.*;

/**
 * 
 */
public class ClientFilter {
    private String name;
    private String email;
    private Date signupDate;
    private EClientType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public EClientType getType() {
        return type;
    }

    public void setType(EClientType type) {
        this.type = type;
    }
}