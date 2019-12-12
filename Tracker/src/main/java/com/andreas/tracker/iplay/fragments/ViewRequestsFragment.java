package main.java.com.andreas.tracker.iplay.fragments;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.model.Torrent;
import org.w3c.dom.Document;

import java.io.IOException;

/**
 * Created by Andreas on 8/19/16.
 */
public class ViewRequestsFragment{

    @FXML
    private Text TextTorrentName;
    @FXML
    private Text TextTorrentGenre;
    @FXML
    private Text TextTorrentType;
    @FXML
    private Text TextTorrentAdded;
    @FXML
    private Text TextTorrentAddedBy;
    @FXML
    private WebView TextDescription;
    private Torrent torrent;

    public void initialize() {
        //set request details
        torrent= Shared.view_request;
        TextTorrentName.setText(torrent.getName());
        TextTorrentGenre.setText(torrent.getGenre());
        TextTorrentType.setText(torrent.getType());
        TextTorrentAdded.setText(torrent.getAdded());
        TextTorrentAddedBy.setText(torrent.getAddedBy());
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
    public void report_request() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.STAFF);
    }
}
