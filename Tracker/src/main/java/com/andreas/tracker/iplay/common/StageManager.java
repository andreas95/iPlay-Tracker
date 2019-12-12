package main.java.com.andreas.tracker.iplay.common;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Andreas on 6/14/16.
 */
public class StageManager {
    private static Stage stage;
    private static VBox pane;

    public StageManager(Stage stage) throws IOException {
        StageManager.stage=stage;
        Parent root;
        stage.setTitle("iPlay Tracker");
        if (!Shared.last_version.getVersion().equals(Shared.version)) {
            root = FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/updater_fragment.fxml"));
        } else {
            root = FXMLLoader.load(ScreenController.class.getResource("/main/java/com/andreas/tracker/iplay/layout/login_fragment.fxml"));
        }
        Scene s=new Scene(root);
        stage.setScene(s);
        stage.show();
    }

    public static Stage getStage() {return stage;}
    public static void setRoot(Parent root) {StageManager.stage.getScene().setRoot(root);}
    public static void setPane(VBox pane) {StageManager.pane=pane;}
    public static void setPaneFragment(Parent root) {
        StageManager.pane.getChildren().setAll(root);
    }
}
