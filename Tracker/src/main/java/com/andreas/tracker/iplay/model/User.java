package main.java.com.andreas.tracker.iplay.model;

/**
 * Created by Andreas on 6/22/16.
 */
public class User {

    private String username;
    private String password;
    private String email;
    private String gender;
    private String joinDate;
    private String lastSeen;
    private float uploaded;
    private float downloaded;
    private float ratio;
    private int invites;
    private String invitedBy;
    private String type;
    private int numberComments;
    private int numberThanks;
    private int numberPosts;
    private String country;
    private String secret_question;
    private String response_secret_question;

    public User() {}
    public User(String username, String password, String email, String gender, String country, String secret_question, String respons_secret_question) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.gender=gender;
        this.country=country;
        this.secret_question=secret_question;
        this.response_secret_question=respons_secret_question;
    }

    public User(String username, String type, String joinDate, String lastSeen, String email, float uploaded, float downloaded, float ratio,
                int invites, String invitedBy, int numberComments, int numberPosts, int numberThanks, String country) {
        this.username=username;
        this.type=type;
        this.joinDate=joinDate;
        this.lastSeen=lastSeen;
        this.email=email;
        this.uploaded=uploaded;
        this.downloaded=downloaded;
        this.ratio=ratio;
        this.invites=invites;
        this.invitedBy=invitedBy;
        this.numberComments=numberComments;
        this.numberPosts=numberPosts;
        this.numberThanks=numberThanks;
        this.country=country;
    }


    public String getUsername() {return username;}
    public String getPassword() {return password;}
    public String getEmail() {return email;}
    public String getGender() {return gender;}
    public String getJoinDate() {return joinDate;}
    public String getLastSeen() {return lastSeen;}
    public float getUploaded() {return uploaded;}
    public float getDownloaded() {return downloaded;}
    public float getRatio() {return ratio;}
    public int getInvites() {return invites;}
    public String getInvitedBy() {return invitedBy;}
    public int getNumberComments() {return numberComments;}
    public int getNumberThanks() {return numberThanks;}
    public int getNumberPosts() {return numberPosts;}
    public String getType() {return type;}
    public String getCountry() {return country;}
    public String getSecret_question() {return secret_question;}
    public String getResponse_secret_question() {return response_secret_question;}
}
