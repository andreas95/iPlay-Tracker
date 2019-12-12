package main.java.com.andreas.tracker.iplay.common;


import javafx.scene.image.Image;
import main.java.com.andreas.tracker.iplay.model.Ticket;
import main.java.com.andreas.tracker.iplay.model.Torrent;
import main.java.com.andreas.tracker.iplay.model.User;
import main.java.com.andreas.tracker.iplay.model.Version;

/**
 * Created by Andreas on 6/16/16.
 */
public class Shared {

    public static ScreenController.Screen screen;
    public static String version;
    public static Version last_version;
    public static String user;
    public static String type;
    public static String join_date;
    public static float ratio;
    public static float uploaded;
    public static float downloaded;
    public static int invites;
    public static int inbox;
    public static int sentbox;
    public static int upload_active;
    public static int download_active;
    public static String news_pane;
    public static String poll_pane;
    public static boolean vote_last_poll;
    public static User view_profile;
    public static Torrent view_torrent;
    public static Torrent view_request;
    public static Ticket view_ticket;
    public static boolean thanks_this_torrent;
    public static String message_type;
    public static String message_text;
    public static final Image img_error = new Image(Shared.class.getResourceAsStream("/main/resources/img/message/error.png"));
    public static final Image img_question = new Image(Shared.class.getResourceAsStream("/main/resources/img/message/question.png"));
    public static final Image img_warn = new Image(Shared.class.getResourceAsStream("/main/resources/img/message/warn.png"));
    public static final Image img_accept = new Image(Shared.class.getResourceAsStream("/main/resources/img/message/accept.png"));
    public static final Image img_solved_yes = new Image(Shared.class.getResourceAsStream("/main/resources/img/common/button/check.png"));
    public static final Image img_solved_no = new Image(Shared.class.getResourceAsStream("/main/resources/img/common/button/delete.png"));
}
