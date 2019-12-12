package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.MaintenanceDB;
import main.java.com.andreas.tracker.iplay.database.TorrentsDB;
import main.java.com.andreas.tracker.iplay.model.Torrent;
import main.java.com.andreas.tracker.iplay.utils.SendEmailToUser;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 6/22/16.
 */
public class UploadFragment {

    @FXML
    private Text TextUser;
    @FXML
    private Text TextJoinDate;
    @FXML
    private TextField FieldSpeedInternal;
    @FXML
    private TextField FieldSpeedExternal;
    @FXML
    private TextField FieldReleases;
    @FXML
    private RadioButton ButtonYesUploader;
    @FXML
    private RadioButton ButtonNoUploader;
    @FXML
    private Button ButtonCancelApp;
    @FXML
    private Button ButtonSendApp;
    @FXML
    private ComboBox ComboCategory;
    @FXML
    private TextField FieldName;
    @FXML
    private TextField FieldLink1;
    @FXML
    private TextField FieldLink2;
    @FXML
    private TextField FieldGenre;
    @FXML
    private TextArea TextDescription;
    @FXML
    private CheckBox CheckBlueTag;
    @FXML
    private CheckBox CheckGreenTag;
    @FXML
    private CheckBox CheckRedTag;
    @FXML
    private CheckBox CheckWhiteTag;
    @FXML
    private CheckBox CheckBlackTag;
    @FXML
    private Button ButtonCancel;
    @FXML
    private Button ButtonSave;
    @FXML
    private TextField FieldSize;
    @FXML
    private ComboBox ComboSize;
    @FXML
    private VBox Main;
    @FXML
    private VBox PaneUpload;
    @FXML
    private VBox PaneAccessDenied;
    @FXML
    private VBox PaneUploadApplication;
    @FXML
    private Text Link;
    private HashMap<String, String> map_categories=new HashMap<String, String>();
    private final String categories="Apple/Mac, Apps, Foreign, Games/PC, Games/PS2, Games/PS3, Games/PSP, Games/Wii," +
            " Games/X360, Linux/Unix, MSC, Mobile/Appz, Mobile/Movies, Movies/AVI.HD, Movies/AVI.HD.RO, Movies/BD," +
            " Movies/BD.RO, Movies/DVDR, Movies/DVDR.RO, Movies/HD, Movies/HD.RO, Movies/PSP, Movies/XVID, Sport," +
            " TV/Boxsets, TV/Episodes, TV/HD, Docs, Music, Adult";
    private final String categories_type="Apple, Apps, , Games.PC, Games.PS2, Games.PS3, Games.PSP, " +
            "Games.Wii, Games.X360, Unix, MISC, Mobile.Appz, Mobile.Movies, AVI.HD, AVI.HD.RO, BD, BD.RO, DVDR, " +
            "DVDR.RO, HD, HD.RO, PSP, XVID, Sport, TV.Boxsets, TV.Episodes, TV.HD, Docs, Music, Adult";

    public void initialize() {
        Main.getChildren().remove(PaneUploadApplication);
        if (Shared.type.equals("SysOp") || Shared.type.equals("Uploader")) {
            Main.getChildren().remove(PaneAccessDenied);

            //initialize combo category
            ComboCategory.getItems().addAll(categories.split(", "));
            ComboCategory.setValue("None");

            //create hash map categories
            String[] split_type = categories_type.split(", ");
            String[] split_category = categories.split(", ");
            for (int i = 0; i < split_type.length; i++) {
                map_categories.put(split_category[i], split_type[i]);
            }

            //combo size
            ComboSize.getItems().addAll("KB", "MB", "GB", "TB");
            ComboSize.setValue("GB");

            //text description
            TextDescription.setText("<html>\n<body style='background-color: #191919;' contenteditable='false'>\n<font face='Segoe UI' color='#ffffff'>\nEnter content\n</font>\n</body>\n</html>");
        } else {
            Main.getChildren().remove(PaneUpload);
            Link.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
            Link.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        }
    }

    @FXML
    public void save() throws IOException, SQLException {
        if (ComboCategory.getValue().equals("None") || FieldName.getText().isEmpty() ||
                FieldGenre.getText().isEmpty() || FieldLink1.getText().isEmpty() ||
                FieldLink2.getText().isEmpty() || FieldSize.getText().isEmpty() ||
                TextDescription.getText().isEmpty()) {
            Shared.message_type = "error";
            Shared.message_text = "Don't leave field blank.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            String tags = "";
            Date data = new Date();
            String date_format = String.format("%tF %<tT", data);
            if (CheckBlueTag.isSelected()) {
                tags += "blue";
            }
            if (CheckGreenTag.isSelected()) {
                if (tags.isEmpty()) {
                    tags += "green";
                } else {
                    tags += ",green";
                }
            }
            if (CheckRedTag.isSelected()) {
                if (tags.isEmpty()) {
                    tags += "red";
                } else {
                    tags += ",red";
                }
            }
            if (CheckWhiteTag.isSelected()) {
                if (tags.isEmpty()) {
                    tags += "white";
                } else {
                    tags += ",white";
                }
            }
            if (CheckBlackTag.isSelected()) {
                if (tags.isEmpty()) {
                    tags += "black";
                } else {
                    tags += ",black";
                }
            }
            if (
            TorrentsDB.addTorrent(new Torrent(map_categories.get(ComboCategory.getValue().toString()), FieldName.getText(), FieldGenre.getText(),
                    tags, FieldLink1.getText(), FieldLink2.getText(), TextDescription.getText().toString(),
                    date_format, FieldSize.getText() + " " + ComboSize.getValue().toString()))) {
                Shared.message_type = "accept";
                Shared.message_text = "The torrent are saved.";
                ScreenController.setScreen(ScreenController.Screen.MESSAGE);
            } else {
                Shared.message_type = "warn";
                Shared.message_text = "The torrent name already exists.";
                ScreenController.setScreen(ScreenController.Screen.MESSAGE);
            }
        }
    }

    @FXML
    public void cancel() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.UPLOAD);
    }

    @FXML
    public void upload_app() {
        Main.getChildren().remove(PaneAccessDenied);
        Main.getChildren().add(PaneUploadApplication);
        ButtonCancelApp.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonCancelApp.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonSendApp.setOnMouseEntered(e -> getStage().getScene().setCursor(Cursor.HAND));
        ButtonSendApp.setOnMouseExited(e -> getStage().getScene().setCursor(Cursor.DEFAULT));
        ToggleGroup buttonGroup=new ToggleGroup();
        ButtonYesUploader.setToggleGroup(buttonGroup);
        ButtonNoUploader.setToggleGroup(buttonGroup);
        ButtonNoUploader.setSelected(true);
        TextUser.setText(Shared.user);
        TextJoinDate.setText(Shared.join_date);
    }

    @FXML
    public void send_app() throws IOException {
        if (FieldReleases.getText().isEmpty() || FieldSpeedInternal.getText().isEmpty() || FieldSpeedExternal.getText().isEmpty()) {
            Shared.message_type="error";
            Shared.message_text="Don't leave field blank.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else if (MaintenanceDB.send_uploader_application(FieldSpeedInternal.getText(),FieldSpeedExternal.getText(),FieldReleases.getText(),ButtonYesUploader.isSelected()?"Yes":"No")){
            Shared.message_type="accept";
            Shared.message_text="Your application has been sent.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            Shared.message_type="error";
            Shared.message_text="Your request already exist.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        }
    }
}
