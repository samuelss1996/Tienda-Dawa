package model.helper.mail;

import model.vo.User;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class MailAgent {

    /**
     * @param user 
     * @param mail
     */
    public void sendMail(User user, Mail mail) {
        // TODO implement here
    }

    /**
     * @param users 
     * @param mail
     */
    public void sendMail(List<User> users, Mail mail) {
        // TODO implement here
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}