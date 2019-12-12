package main.java.com.andreas.tracker.iplay;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.common.StageManager;
import main.java.com.andreas.tracker.iplay.database.ConnectDB;
import main.java.com.andreas.tracker.iplay.database.MaintenanceDB;

/**
 * Created by Andreas on 6/20/16.
 */

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        new ConnectDB();
        Shared.version="1.0.0 beta";
        Shared.last_version= MaintenanceDB.getLastVersion();
        new StageManager(primaryStage);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/main/resources/img/icon/App_Play_Icon_48.png")));
    }


    public static void main(String[] args) {
        launch(args);
    }
}

