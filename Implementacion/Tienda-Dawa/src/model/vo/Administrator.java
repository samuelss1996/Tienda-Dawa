package model.vo;

import java.util.Date;

/**
 * 
 */
public class Administrator extends User {
    public Administrator(int id, String username, String email, Date signupDate) {
        super(id, username, email, signupDate);
    }
}