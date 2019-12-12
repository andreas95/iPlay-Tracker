package main.java.com.andreas.tracker.iplay.model;

/**
 * Created by Andreas on 8/9/16.
 */
public class Ticket {

    private String category;
    private String subject;
    private String message;
    private String added;
    private String solved;
    private String done_by;
    private String done_in;
    private String answer;

    public Ticket() {}

    public Ticket(String subject,String message, String answer,String user) {
        this.subject=subject;
        this.message=message;
        this.answer=answer;
    }
    public Ticket(String category, String subject, String message) {
        this.category=category;
        this.subject=subject;
        this.message=message;
    }

    public Ticket(String category, String subject, String message, String added, String solved, String done_by, String done_in) {
        this.category=category;
        this.subject=subject;
        this.message=message;
        this.added=added;
        this.solved=solved;
        this.done_by=done_by;
        this.done_in=done_in;
    }

    public String getCategory() {return category;}
    public String getSubject() {return subject;}
    public String getMessage() {return message;}
    public String getAdded() {return added;}
    public String getSolved() {return solved;}
    public String getDone_by() {return done_by;}
    public String getDone_in() {return done_in;}
    public String getAnswer() {return answer;}
}
