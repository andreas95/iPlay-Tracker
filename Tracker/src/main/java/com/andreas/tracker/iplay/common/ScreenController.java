package main.java.com.andreas.tracker.iplay.common;

import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Created by Andreas on 6/14/16.
 */
public class ScreenController {

    public static enum Screen {
        LOGIN,
        INTERFACE,
        HOME,
        BROWSE,
        IDOCS,
        MUSIC,
        ADULT,
        REQUESTS,
        UPLOAD,
        PROFILE,
        FORUM,
        DONATE,
        GUIDES,
        IRC,
        RULES,
        STAFF,
        LINKS,
        USERS,
        DISCLAIMER,
        TOP10,
        FAQ,
        RSS,
        SETUPRSS,
        NEWS,
        MESSAGE,
        POLL,
        NOINTERNET,
        VIEWPROFILE,
        VIEWTORRENT,
        VIEWTICKET,
        VIEWREQUESTS,
        UPDATER
    }

    public ScreenController() {
    }

    public static void setScreen(Screen screen) throws IOException {
        switch (screen) {
            case LOGIN:
                StageManager.setRoot(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/login_fragment.fxml")));
                Shared.screen= Screen.LOGIN;
                break;
            case UPDATER:
                StageManager.setRoot(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/updater_fragment.fxml")));
                Shared.screen= Screen.UPDATER;
                break;
            case INTERFACE:
                StageManager.setRoot(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/interface_fragment.fxml")));
                Shared.screen= Screen.INTERFACE;
                break;
            case HOME:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/home_fragment.fxml")));
                Shared.screen=Screen.HOME;
                break;
            case BROWSE:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/browse_fragment.fxml")));
            Shared.screen=Screen.BROWSE;
            break;
            case IDOCS:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/idocs_fragment.fxml")));
            Shared.screen=Screen.IDOCS;
            break;
            case MUSIC:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/music_fragment.fxml")));
            Shared.screen=Screen.MUSIC;
            break;
            case ADULT:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/adult_fragment.fxml")));
            Shared.screen=Screen.ADULT;
            break;
            case REQUESTS:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/requests_fragment.fxml")));
            Shared.screen=Screen.REQUESTS;
            break;
            case UPLOAD:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/upload_fragment.fxml")));
            Shared.screen=Screen.UPLOAD;
            break;
            case PROFILE:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/profile_fragment.fxml")));
            Shared.screen=Screen.PROFILE;
            break;
            case FORUM:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/forum_fragment.fxml")));
            Shared.screen=Screen.FORUM;
            break;
            case DONATE:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/donate_fragment.fxml")));
            Shared.screen=Screen.DONATE;
            break;
            case GUIDES:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/guides_fragment.fxml")));
            Shared.screen=Screen.GUIDES;
            break;
            case IRC:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/irc_fragment.fxml")));
            Shared.screen=Screen.IRC;
            break;
            case RULES:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/rules_fragment.fxml")));
            Shared.screen=Screen.RULES;
            break;
            case STAFF:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/staff_fragment.fxml")));
            Shared.screen=Screen.STAFF;
            break;
            case LINKS:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/links_fragment.fxml")));
            Shared.screen=Screen.LINKS;
            break;
            case USERS:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/users_fragment.fxml")));
            Shared.screen=Screen.USERS;
            break;
            case DISCLAIMER:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/disclaimer_fragment.fxml")));
            Shared.screen=Screen.DISCLAIMER;
            break;
            case TOP10:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/top10_fragment.fxml")));
            Shared.screen=Screen.TOP10;
            break;
            case FAQ:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/FAQ_fragment.fxml")));
            Shared.screen=Screen.FAQ;
            break;
            case RSS:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/rss_fragment.fxml")));
            Shared.screen=Screen.RSS;
            break;
            case SETUPRSS:
            StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/setuprss_fragment.fxml")));
            Shared.screen=Screen.SETUPRSS;
            break;
            case NEWS:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/news_fragment.fxml")));
                Shared.screen=Screen.NEWS;
                break;
            case MESSAGE:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/message_fragment.fxml")));
                Shared.screen=Screen.MESSAGE;
                break;
            case POLL:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/poll_fragment.fxml")));
                Shared.screen=Screen.POLL;
                break;
            case NOINTERNET:
                StageManager.setRoot(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/no_internet_fragment.fxml")));
                Shared.screen=Screen.NOINTERNET;
                break;
            case VIEWPROFILE:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/view_profile_fragment.fxml")));
                Shared.screen=Screen.POLL;
                break;
            case VIEWTORRENT:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/view_torrent_fragment.fxml")));
                Shared.screen=Screen.POLL;
                break;
            case VIEWTICKET:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/view_ticket_fragment.fxml")));
                Shared.screen=Screen.VIEWTICKET;
                break;
            case VIEWREQUESTS:
                StageManager.setPaneFragment(FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/view_requests_fragment.fxml")));
                Shared.screen=Screen.VIEWREQUESTS;
                break;
            default:
                break;
        }
    }
}
