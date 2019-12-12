package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.Shared;

/**
 * Created by Andreas on 8/20/16.
 */
public class UpdaterFragment {

    @FXML
    private Text TextInstalledVersion;
    @FXML
    private Text TextUpdateVersion;
    @FXML
    private TextArea TextNews;

    public void initialize() {
        TextInstalledVersion.setText("Installed version: "+ Shared.version);
        TextUpdateVersion.setText("Update version: "+Shared.last_version.getVersion());
        TextNews.setText("What's New:\n\n"+Shared.last_version.getNews());
        System.out.print(Shared.last_version.getNews());
    }

    @FXML
    private void update() {

    }
}
