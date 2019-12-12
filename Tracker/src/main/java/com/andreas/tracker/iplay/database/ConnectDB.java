package main.java.com.andreas.tracker.iplay.database;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import main.java.com.andreas.tracker.iplay.ui.AlertMessage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Andreas on 6/15/16.
 */

public class ConnectDB {

    private static Connection myConnection;
/*    private String url="jdbc:mysql://176.32.230.48:3306/cl59-iplay?useSSL=false";
    private String user="cl59-iplay";
    private String pass="TWg/FjddD";*/

    private String url="jdbc:mysql://176.32.230.48:3306/cl59-iplay?useSSL=false";
    private String user="cl59-iplay";
    private String pass="TWg/FjddD";

    public ConnectDB() {
        try {
            ConnectDB.myConnection= DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            new AlertMessage("Error","The server could not be contacted. Please verify your connection.", Alert.AlertType.ERROR);
            Platform.exit();
            System.exit(-1);
        }
    }

    public static Connection getConnection() {
        return myConnection;
    }
}