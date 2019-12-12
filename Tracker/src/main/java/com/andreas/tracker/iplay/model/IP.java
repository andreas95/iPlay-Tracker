package main.java.com.andreas.tracker.iplay.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Andreas on 7/20/16.
 */

public class IP {
    public static String getIp() throws MalformedURLException {
        URL whatismyip = new URL("http://icanhazip.com");
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return "null";
        }
        try {
            return in.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            return "null";
        }
    }
}