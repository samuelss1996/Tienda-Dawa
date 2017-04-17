package model.helper.mail;

public class Mail {
    private final String subject;
    private final String content;

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