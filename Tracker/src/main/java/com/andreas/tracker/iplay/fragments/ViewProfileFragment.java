package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.model.User;

/**
 * Created by Andreas on 7/27/16.
 */
public class ViewProfileFragment {

    @FXML
    private Label TextProfileName;
    @FXML
    private Text TextJoinDate;
    @FXML
    private Text TextLastSeen;
    @FXML
    private Text TextEmail;
    @FXML
    private Text TextDownloaded;
    @FXML
    private Text TextUploaded;
    @FXML
    private Text TextRatio;
    @FXML
    private Text TextInvitedBy;
    @FXML
    private Text TextForumPosts;
    @FXML
    private Text TextTorrentsComments;
    @FXML
    private Text TextTorrentsThanks;
    @FXML
    private Text TextInvites;
    @FXML
    private Text TextClass;


    public void initialize() {
        User user=Shared.view_profile;
        Shared.view_profile=null;
        TextProfileName.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/main/resources/img/common/country/"+user.getCountry()+".gif"))));
        TextProfileName.setText(user.getUsername());
        TextJoinDate.setText(user.getJoinDate());
        TextLastSeen.setText(user.getLastSeen());
        TextEmail.setText(user.getEmail());
        TextDownloaded.setText(String.valueOf(user.getDownloaded()));
        TextUploaded.setText(String.valueOf(user.getUploaded()));
        TextRatio.setText(String.valueOf(user.getRatio()));
        TextInvitedBy.setText(user.getInvitedBy());
        TextForumPosts.setText(String.valueOf(user.getNumberPosts()));
        TextTorrentsComments.setText(String.valueOf(user.getNumberComments()));
        TextTorrentsThanks.setText(String.valueOf(user.getNumberThanks()));
        TextInvites.setText(String.valueOf(user.getInvites()));
        TextClass.setText(user.getType());
    }
}
