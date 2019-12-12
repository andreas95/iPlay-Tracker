package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.Shared;

/**
 * Created by Andreas on 6/22/16.
 */
public class ProfileFragment {

    @FXML
    private RadioButton ButtonParkedYes;
    @FXML
    private Text TextUser;
    @FXML
    private RadioButton ButtonParkedNo;
    @FXML
    private RadioButton ButtonPMAll;
    @FXML
    private RadioButton ButtonPMFriends;
    @FXML
    private RadioButton ButtonPMStaff;
    @FXML
    private CheckBox CheckDeletePM;
    @FXML
    private CheckBox CheckSavePM;
    @FXML
    private CheckBox CheckEmailNotification;
    @FXML
    private ComboBox ComboStylesheet;
    @FXML
    private ComboBox ComboCountry;
    @FXML
    private ComboBox ComboLanguage;
    @FXML
    private TextField FieldEmail;
    @FXML
    private PasswordField FieldOldPassword;
    @FXML
    private PasswordField FieldNewPassword;
    @FXML
    private PasswordField FieldNewPasswordAgain;

    public void initialize() {
        TextUser.setText(Shared.user);
    }
}
