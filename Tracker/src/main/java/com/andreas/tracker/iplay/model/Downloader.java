package main.java.com.andreas.tracker.iplay.model;

import javafx.stage.FileChooser;
import main.java.com.andreas.tracker.iplay.common.StageManager;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Andreas on 7/18/16.
 */
public class Downloader {

    public static void directDownload(String link, String name) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Torrent");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.setInitialFileName(name+".torrent");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Torrent", "*.torrent")
        );
        File file = fileChooser.showSaveDialog(StageManager.getStage());
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ReadableByteChannel rbc = null;
        try {
            rbc = Channels.newChannel(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void magnetDownload() {

    }
}
