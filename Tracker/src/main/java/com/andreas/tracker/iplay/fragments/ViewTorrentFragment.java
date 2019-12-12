package main.java.com.andreas.tracker.iplay.fragments;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.TorrentsDB;
import main.java.com.andreas.tracker.iplay.model.Torrent;
import org.w3c.dom.Document;

import java.io.IOException;
import java.sql.SQLException;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 7/27/16.
 */
public class ViewTorrentFragment {

    @FXML
    private Text TextTorrentName;
    @FXML
    private Label ButtonMagnetLink;
    @FXML
    private Label ButtonDirectLink;
    @FXML
    private Text TextTorrentGenre;
    @FXML
    private Text TextTorrentType;
    @FXML
    private Text TextTorrentSize;
    @FXML
    private Text TextTorrentSnatched;
    @FXML
    private Text TextTorrentAdded;
    @FXML
    private Text TextTorrentUploadedBy;
    @FXML
    private Text ButtonReport;
    @FXML
    private WebView TextDescription;
    @FXML
    private Text TextThanks;
    @FXML
    private Button ButtonThanks;
    private Torrent torrent;

    public void initialize() {
        //set torrent details
        torrent= Shared.view_torrent;
        TextTorrentName.setText(torrent.getName());
        TextTorrentGenre.setText(torrent.getGenre());
        TextTorrentType.setText(torrent.getType());
        TextTorrentSize.setText(torrent.getSize());
        TextTorrentAdded.setText(torrent.getAdded());
        TextTorrentSnatched.setText(torrent.getSnatched()+" time(s)");
        TextTorrentUploadedBy.setText(torrent.getUploader());
        TextThanks.setText(torrent.getThanks());
        if (Shared.thanks_this_torrent) {
            ButtonThanks.setDisable(true);
        }
        WebEngine webEngine = TextDescription.getEngine();
        webEngine.loadContent(torrent.getDescription());
        webEngine.documentProperty().addListener(new ChangeListener<Document>() {
            @Override public void changed(ObservableValue<? extends Document> prop, Document oldDoc, Document newDoc) {
                String heightText = TextDescription.getEngine().executeScript(
                        "window.getComputedStyle(document.body, null).getPropertyValue('height')"
                ).toString();
                double height = Double.valueOf(heightText.replace("px", ""));

                TextDescription.setMinHeight(height+20);
            }
        });

    }

    @FXML
    public void download_direct() {

    }

    @FXML
    public void download_magnet() {

    }

    @FXML
    public void report_torrent() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.STAFF);
    }

    @FXML
    public void thanks() {
        try {
            TorrentsDB.putThanks(torrent.getName());
            Shared.view_torrent=TorrentsDB.getTorrent(torrent.getName());
            ButtonThanks.setDisable(true);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
