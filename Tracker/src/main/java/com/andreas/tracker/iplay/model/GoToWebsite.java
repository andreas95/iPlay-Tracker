package main.java.com.andreas.tracker.iplay.model;

import java.awt.*;
import java.net.URI;

/**
 * Created by Andreas on 7/22/16.
 */
public class GoToWebsite {

    public static void access(String URL) {
        if (Desktop.isDesktopSupported()) {
            // Windows
            try {
                Desktop.getDesktop().browse(new URI(URL));
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            // Ubuntu
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("/usr/bin/firefox -new-window " + URL);
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
}
