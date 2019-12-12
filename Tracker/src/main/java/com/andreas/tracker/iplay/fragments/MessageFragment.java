package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import main.java.com.andreas.tracker.iplay.common.Shared;

import static main.java.com.andreas.tracker.iplay.common.Shared.*;

/**
 * Created by Andreas on 7/5/16.
 */
public class MessageFragment {

    @FXML
    private Text MessageError;
    @FXML
    private Label TypeError;

    public void initialize() {
        switch (Shared.message_type) {
            case "error":
                TypeError.setGraphic(new ImageView(Shared.img_error));
                TypeError.setText("Error");
                break;
            case "accept":
                TypeError.setGraphic(new ImageView(img_accept));
                TypeError.setText("Succes");
                break;
            case "warn":
                TypeError.setGraphic(new ImageView(img_warn));
                TypeError.setText("Error");
                break;
            case "question":
                TypeError.setGraphic(new ImageView(img_question));
                break;
        }
        MessageError.setText(Shared.message_text);
    }

}
