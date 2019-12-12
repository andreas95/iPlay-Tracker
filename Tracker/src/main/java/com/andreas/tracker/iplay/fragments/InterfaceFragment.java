package main.java.com.andreas.tracker.iplay.fragments;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.common.StageManager;
import main.java.com.andreas.tracker.iplay.database.UsersDB;
import main.java.com.andreas.tracker.iplay.model.GoToWebsite;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 6/22/16.
 */
public class InterfaceFragment {

    @FXML
    private HBox BoxStyle;
    @FXML
    private Label ButtoniFeel;
    @FXML
    private Label ButtonStarfall;
    @FXML
    private Label ButtonCoagulate;
    @FXML
    private Label ButtonHelios;
    @FXML
    private Label ButtonTyrian;
    @FXML
    private VBox BoxiFeel;
    @FXML
    private Label ButtonHome;
    @FXML
    private Label ButtonBrowse;
    @FXML
    private Label ButtoniDocs;
    @FXML
    private Label ButtonMusic;
    @FXML
    private Label ButtonAdult;
    @FXML
    private Label ButtonRequests;
    @FXML
    private Label ButtonUpload;
    @FXML
    private Label ButtonProfile;
    @FXML
    private Label ButtonForum;
    @FXML
    private Label ButtonDonate;
    @FXML
    private Label ButtonRules;
    @FXML
    private Label ButtonIrc;
    @FXML
    private Label ButtonGuides;
    @FXML
    private Label ButtonStaff;
    @FXML
    private Label ButtonLinks;
    @FXML
    private Label ButtonUsers;
    @FXML
    private Label ButtonSetupRss;
    @FXML
    private Label ButtonRss;
    @FXML
    private Label ButtonFAQ;
    @FXML
    private Label ButtonTop10;
    @FXML
    private Label ButtonDisclaimer;
    @FXML
    private Text TextUser;
    @FXML
    private Text TextType;
    @FXML
    private Label ButtonStar;
    @FXML
    private Label ButtonChat;
    @FXML
    private Label ButtoniSeed;
    @FXML
    private Label ButtonLogout;
    @FXML
    private HBox top_right;
    @FXML
    private Label TextTime;
    @FXML
    private Label TextDownloadActive;
    @FXML
    private Label TextUploadActive;
    @FXML
    private Text TextUploaded;
    @FXML
    private Text TextDownloaded;
    @FXML
    private Text TextRatio;
    @FXML
    private Label ButtonInbox;
    @FXML
    private Label ButtonSentbox;
    @FXML
    private Label ButtonInvites;
    @FXML
    private Label ButtonFriends;
    @FXML
    private VBox PaneFragment;
    @FXML
    private VBox Main;
    @FXML
    private ScrollPane Sp;
    @FXML
    private VBox PaneSocialMedia;
    @FXML
    private Label ButtonClose;
    @FXML
    private Button ButtonGooglePlus;
    @FXML
    private Button ButtonTwitter;
    @FXML
    private Button ButtonTumblr;
    @FXML
    private Button ButtonFacebook;

    public void initialize() throws IOException {
        //TODO scroll for this pane
       // closePaneMedia();
        TextUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Shared.view_profile= UsersDB.getUser(TextUser.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ScreenController.setScreen(ScreenController.Screen.VIEWPROFILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Pane Fragment
        StageManager.setPane(PaneFragment);
        home();
        Sp.setContent(Main);


        //fixed social media pane
        //TODO

        //Mouse pointer
        TextUser.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        TextUser.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
      /*  ButtonClose.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonClose.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonTwitter.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonTwitter.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonFacebook.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonFacebook.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonTumblr.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonTumblr.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonGooglePlus.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonGooglePlus.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));*/
        ButtoniFeel.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtoniFeel.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonHelios.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonHelios.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonStarfall.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonStarfall.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonTyrian.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonTyrian.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonCoagulate.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonCoagulate.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonHome.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonHome.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonBrowse.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonBrowse.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtoniDocs.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtoniDocs.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonAdult.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonAdult.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonMusic.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonMusic.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonForum.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonForum.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonProfile.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonProfile.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonRules.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonRules.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonRequests.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonRequests.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonDonate.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonDonate.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonIrc.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonIrc.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonGuides.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonGuides.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonStaff.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonStaff.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonUpload.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonUpload.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonLinks.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonLinks.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonUsers.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonUsers.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonTop10.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonTop10.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonDisclaimer.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonDisclaimer.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonFAQ.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonFAQ.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonRss.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonRss.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonSetupRss.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonSetupRss.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
    /*    ButtonStar.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonStar.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonChat.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonChat.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtoniSeed.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtoniSeed.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));*/
        ButtonLogout.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonLogout.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
       /* ButtonInbox.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonInbox.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonSentbox.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonSentbox.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonInvites.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonInvites.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonFriends.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonFriends.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));*/

        //user bar info
        TextUser.setText(Shared.user);
        TextType.setText(Shared.type);
        bindToTime(); //date and clock user bar
        TextDownloadActive.setText(String.valueOf(Shared.download_active));
        TextUploadActive.setText(String.valueOf(Shared.upload_active));
        TextDownloaded.setText(String.valueOf(Shared.downloaded));
        TextUploaded.setText(String.valueOf(Shared.uploaded));
        TextRatio.setText(String.valueOf(Shared.ratio));
        ButtonInbox.setText(String.valueOf(Shared.inbox));
        ButtonSentbox.setText(String.valueOf(Shared.sentbox));
        ButtonInvites.setText(String.valueOf(Shared.invites));

        BoxiFeel.getChildren().remove(BoxStyle);

    }

    @FXML
    private void ifeel() {
        if (BoxiFeel.getChildren().contains(BoxStyle)) {
            BoxiFeel.getChildren().remove(BoxStyle);
        } else {
            BoxiFeel.getChildren().add(BoxStyle);
        }
    }

    @FXML
    private void starfall() {

    }

    @FXML
    private void tyrian() {

    }

    @FXML
    private void helios() {

    }

    @FXML
    private void coagulate() {

    }

    @FXML
    private void home() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.HOME);
    }

    @FXML
    private void browse() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.BROWSE);
    }

    @FXML
    private void idocs() throws  IOException{
        ScreenController.setScreen(ScreenController.Screen.IDOCS);
    }

    @FXML
    private void music() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.MUSIC);
    }

    @FXML
    private void adult() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.ADULT);
    }

    @FXML
    private void requests() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.REQUESTS);
    }

    @FXML
    private void upload() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.UPLOAD);
    }

    @FXML
    private void profile() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.PROFILE);
    }

    @FXML
    private void forum() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.FORUM);
    }

    @FXML
    private void donate() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.DONATE);
    }

    @FXML
    private void guides() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.GUIDES);
    }

    @FXML
    private void irc() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.IRC);
    }

    @FXML
    private void rules() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.RULES);
    }

    @FXML
    private void staff() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.STAFF);
    }

    @FXML
    private void users() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.USERS);
    }

    @FXML
    private void links() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.LINKS);
    }

    @FXML
    private void top10() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.TOP10);
    }

    @FXML
    private void disclaimer() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.DISCLAIMER);
    }

    @FXML
    private void faq() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.FAQ);
    }

    @FXML
    private void rss() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.RSS);
    }

    @FXML
    private void setuprss() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.SETUPRSS);
    }

    @FXML
    private void logout() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.LOGIN);
    }

    private void bindToTime() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                               Date data = new Date();
                               String date_format=String.format("%tA, %<tB %<td, %<tY %<tr", data);
                                TextTime.setText(date_format);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void closePaneMedia() {
        Main.getChildren().remove(PaneSocialMedia);
    }

    @FXML
    private void facebook() {
        GoToWebsite.access("https://facebook.com/");
    }

    @FXML
    private void twitter() {
        GoToWebsite.access("https://twitter.com/");
    }

    @FXML
    private void googleplus() {
        GoToWebsite.access("https://google.ro/");
    }

    @FXML
    private void tumblr() {
        GoToWebsite.access("https://tumblr.com/");
    }
}
