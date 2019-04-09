package net.thumbtack.school.backend.threads.exercise_14;

public class Message {

    private String emailAddress;
    private String sender;
    private String subject;
    private String body;

    public Message(){
    }

    public Message(String emailAddress, String sender, String subject, String body){
        this.emailAddress = emailAddress;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public Message(String emailAddress) {
        this.emailAddress = emailAddress;
        System.out.println(this);

    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "emailAddress='" + emailAddress + '\'' +
                ", sender='" + sender + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
