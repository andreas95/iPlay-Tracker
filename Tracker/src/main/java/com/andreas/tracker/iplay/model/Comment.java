package main.java.com.andreas.tracker.iplay.model;

/**
 * Created by Andreas on 8/20/16.
 */
public class Comment {

    private String user;
    private String torrent;
    private String added;
    private String text;
    private String ori_text;
    private String edited_by;
    private String edited_at;

    public Comment() {}

    //constructor for add comment
    public Comment(String user, String torrent, String added, String text) {
        this.user=user;
        this.torrent=torrent;
        this.added=added;
        this.text=text;
    }

    //constructor for edit comment
    public Comment(String user, String torrent, String added, String text, String ori_text, String edited_at, String edited_by) {
        this.user=user;
        this.torrent=torrent;
        this.added=added;
        this.text=text;
        this.ori_text=ori_text;
        this.edited_at=edited_at;
        this.edited_by=edited_by;
    }


    public String getUser() {return user;}
    public String getTorrent() {return torrent;}
    public String getText() {return text;}
    public String getAdded() {return added;}
    public String getEdited_by() {return edited_by;}
    public String getEdited_at() {return edited_at;}
    public String getOri_text() {return ori_text;}

}
