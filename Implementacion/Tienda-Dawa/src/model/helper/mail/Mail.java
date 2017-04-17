package model.helper.mail;

/**
 * 
 */
public class Mail {
    private String subject;
    private String content;

    public Mail(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}