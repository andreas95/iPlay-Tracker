package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.NewsDB;
import main.java.com.andreas.tracker.iplay.model.News;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 7/3/16.
 */
public class NewsFragment {

    @FXML
    private VBox PaneNews;
    @FXML
    private VBox PaneAdd;
    @FXML
    private VBox PaneEdit;
    @FXML
    private VBox PaneDelete;
    @FXML
    private Button ButtonSaveNews;
    @FXML
    private Button ButtonCancel;
    @FXML
    private TextArea NewsContent;
    @FXML
    private TextField FieldTitle;
    @FXML
    private GridPane PaneEditScene1;
    @FXML
    private GridPane PaneEditScene2;
    @FXML
    private ComboBox ListNews;
    @FXML
    private Button ButtonEditNews;
    @FXML
    private Button ButtonCancelNews;
    @FXML
    private TextField FieldTitleEdit;
    @FXML
    private TextArea NewsContentEdit;
    @FXML
    private ComboBox ListNewsDelete;
    @FXML
    private Button ButtonEditNewsNext;
    @FXML
    private Button ButtonDeleteNews;
    @FXML
    private Button ButtonCancelDelete;

    public void initialize() throws SQLException {
        ListNews.getItems().addAll(NewsDB.getTitlesNews());
        ListNewsDelete.getItems().addAll(NewsDB.getTitlesNews());

        PaneEdit.getChildren().remove(PaneEditScene2);
        NewsContent.setText("<html>\n<body style='background-color: #161616;' contenteditable='false'>\n<font face='Segoe UI' color='#ffffff'>\nEnter news content\n</font>\n</body>\n</html>");


        PaneNews.getChildren().removeAll(PaneAdd,PaneEdit,PaneDelete);
        switch (Shared.news_pane) {
            case "Add":
                PaneNews.getChildren().add(PaneAdd);
                break;
            case "Edit":
                PaneNews.getChildren().add(PaneEdit);
                break;
            case "Delete":
                PaneNews.getChildren().add(PaneDelete);
                break;
        }
    }

    @FXML
    public void cancel() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.HOME);
    }

    @FXML
    public void saveNews() throws SQLException, IOException {
        if (FieldTitle.getText()==null || FieldTitle.getText().isEmpty()) {
            Shared.message_text="Error";
            Shared.message_type="error";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            Date data = new Date();
            String date_format = String.format("%tF", data);
            NewsDB.addNews(new News(FieldTitle.getText(), NewsContent.getText(), date_format));
            Shared.message_text="Success";
            Shared.message_type="accept";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        }
    }

    @FXML
    public void edit() throws SQLException {
        if (!ListNews.getValue().toString().equals("---- None selected ----")) {
            PaneEdit.getChildren().remove(PaneEditScene1);
            FieldTitleEdit.setText(ListNews.getValue().toString());
            NewsContentEdit.setText(NewsDB.getContentNews(ListNews.getValue().toString()));
            PaneEdit.getChildren().add(PaneEditScene2);
        }
    }

    @FXML
    public void editNews() throws SQLException, IOException {
        if (FieldTitleEdit.getText()==null || FieldTitleEdit.getText().isEmpty()) {
            Shared.message_text="Error";
            Shared.message_type="error";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            Date data = new Date();
            String date_format = String.format("%tF", data);
            NewsDB.editNews(new News(FieldTitleEdit.getText(), NewsContentEdit.getText(), date_format), ListNews.getValue().toString());
            Shared.message_text="Success";
            Shared.message_type="accept";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        }
    }

    @FXML
    public void delete() throws IOException, SQLException {
        if (!ListNewsDelete.getValue().toString().equals("---- None selected ----")) {
            try {
                NewsDB.deleteNews(ListNewsDelete.getValue().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Shared.message_text="Success";
            Shared.message_type="accept";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        }
    }
}
