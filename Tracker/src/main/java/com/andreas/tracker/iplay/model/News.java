package main.java.com.andreas.tracker.iplay.model;

/**
 * Created by Andreas on 7/3/16.
 */
public class News {

    private String title;
    private String content;
    private String date;

    public News() {}
    public News(String title, String content, String date) {
        this.title=title;
        this.content=content;
        this.date=date;
    }

    public String getTitle() {return title;}
    public String getContent() {return content;}
    public String getDate() {return date;}
}
