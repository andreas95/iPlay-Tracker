package main.java.com.andreas.tracker.iplay.fragments;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.java.com.andreas.tracker.iplay.animation.FadeInUpTransition;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.UsersDB;
import main.java.com.andreas.tracker.iplay.model.GoToWebsite;
import main.java.com.andreas.tracker.iplay.model.IP;
import main.java.com.andreas.tracker.iplay.model.User;
import main.java.com.andreas.tracker.iplay.utils.SendEmailToUser;
import main.java.com.andreas.tracker.iplay.utils.ValidateEmail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import static main.java.com.andreas.tracker.iplay.common.Shared.*;
import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;
import static sun.jvm.hotspot.runtime.PerfMemory.start;

/**
 * Created by Andreas on 6/20/16.
 */
public class LoginFragment {

    @FXML
    private Button ButtonLogin;
    @FXML
    private Button ButtonRecover;
    @FXML
    private Button ButtonSignup;
    @FXML
    private GridPane PaneMain;
    @FXML
    private VBox PaneLogin;
    @FXML
    private VBox PaneRecover;
    @FXML
    private VBox PaneSignup;
    @FXML
    private VBox PaneMessage;
    @FXML
    private Text TextCountLogin;
    @FXML
    private TextField FieldUsername;
    @FXML
    private PasswordField FieldPassword;
    @FXML
    private TextField FieldEmail;
    @FXML
    private TextField FieldRegisterUsername;
    @FXML
    private PasswordField FieldRegisterPassword;
    @FXML
    private PasswordField FieldRegisterPasswordAgain;
    @FXML
    private TextField FieldRegisterEmail;
    @FXML
    private Button ButtonForLogin;
    @FXML
    private Button ButtonForRecover;
    @FXML
    private Button ButtonForSignup;
   // @FXML
   // private Button ButtonTrialAccount;
    @FXML
    private RadioButton ButtonMale;
    @FXML
    private RadioButton ButtonFemale;
    @FXML private Text MessageError;
    @FXML
    private Label TypeError;
    @FXML
    private TextField FieldCharactersRegister;
    @FXML
    private TextField FieldCharactersRecover;
    @FXML
    private ComboBox ComboSecretQuestion;
    @FXML
    private ComboBox ComboCountry;
    @FXML
    private TextField FieldSecretResponse;
    @FXML
    private CheckBox CheckAge;
    @FXML
    private CheckBox CheckRules;
    @FXML
    private CheckBox CheckFAQ;
    @FXML
    private Text LinkRules;
    @FXML
    private Text LinkFAQ;
    @FXML
    private VBox PaneRules;
    @FXML
    private VBox PaneFAQ;
    @FXML
    private Text TextPosting;
    @FXML
    private ScrollPane Sp;


    private final ToggleGroup Gender=new ToggleGroup();
    private int countLogin=6;

    public void initialize() {
        Sp.setContent(PaneMain);
       // checkInternetConnection();

        //initialize combo boxs

        ComboSecretQuestion.getItems().addAll("---- None selected ----",
                "Mother's birthplace",
                "Best childhood friend",
                "Name of first pet" ,
                "Favorite teacher" ,
                "Favorite historical person" ,
                "Grandfather's occupation" ,
                "Mother's occupation" ,
                "Father's occupation",
                "Your pet name");

        ComboCountry.getItems().addAll("---- None selected ----",
                "Andorra",
                "Austria",
                "France",
                "Germany",
                "Greece",
                "Hungary",
                "Italy",
                "Netherlands",
                "Poland",
                "Portugal",
                "Romania",
                "Russia",
                "Spain",
                "Sweden",
                "Turkey",
                "UK",
                "USA");

        ButtonMale.setToggleGroup(Gender);
        ButtonFemale.setToggleGroup(Gender);

        TextCountLogin.setText(Integer.toString(countLogin));
        PaneMain.getChildren().removeAll(PaneLogin,PaneRecover,PaneSignup,PaneMessage,PaneRules,PaneFAQ);

        //set focus

        //login
        FieldUsername.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    FieldPassword.requestFocus();
                }
            }
        });
        FieldPassword.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()==KeyCode.ENTER) {
                    try {
                        login();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //recover
        FieldEmail.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    FieldCharactersRecover.requestFocus();
                }
            }
        });
        FieldCharactersRecover.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()==KeyCode.ENTER) {
                    try {
                        recover();
                    } catch (SQLException e) {
                        e.printStackTrace();
                }}
            }
        });


        //signup
        FieldRegisterUsername.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    FieldRegisterPassword.requestFocus();
                }
            }
        });
        FieldRegisterPassword.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    FieldRegisterPasswordAgain.requestFocus();
                }
            }
        });
        FieldRegisterPasswordAgain.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    FieldRegisterEmail.requestFocus();
                }
            }
        });
        FieldRegisterEmail.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void  handle(KeyEvent keyEvent)
            {
                if (keyEvent.getCode()== KeyCode.ENTER) {
                    FieldCharactersRegister.requestFocus();
                }
            }
        });
    }

    @FXML
    public void showLoginPane() {
        if (PaneMain.getChildren().contains(PaneMessage)) {
            PaneMain.getChildren().remove(PaneMessage);
        } else if (PaneMain.getChildren().contains(PaneRecover)) {
            PaneMain.getChildren().remove(PaneRecover);
        } else if (PaneMain.getChildren().contains(PaneSignup)) {
            PaneMain.getChildren().remove(PaneSignup);
        } else if (PaneMain.getChildren().contains(PaneRules)) {
            PaneMain.getChildren().remove(PaneRules);
        } else if (PaneMain.getChildren().contains(PaneFAQ)) {
            PaneMain.getChildren().remove(PaneFAQ);
        }
        if (PaneMain.getChildren().contains(PaneLogin)) {
            PaneMain.getChildren().remove(PaneLogin);
        } else {
            clear();
            PaneMain.getChildren().add(PaneLogin);
            new FadeInUpTransition(PaneLogin).play();
        }
    }

    @FXML
    public void showRecoverPane() {
        if (PaneMain.getChildren().contains(PaneMessage)) {
            PaneMain.getChildren().remove(PaneMessage);
        } else if (PaneMain.getChildren().contains(PaneLogin)) {
            PaneMain.getChildren().remove(PaneLogin);
        } else if (PaneMain.getChildren().contains(PaneSignup)) {
            PaneMain.getChildren().remove(PaneSignup);
        } else if (PaneMain.getChildren().contains(PaneRules)) {
            PaneMain.getChildren().remove(PaneRules);
        } else if (PaneMain.getChildren().contains(PaneFAQ)) {
            PaneMain.getChildren().remove(PaneFAQ);
        }
        if (PaneMain.getChildren().contains(PaneRecover)) {
            PaneMain.getChildren().remove(PaneRecover);
        } else {
            clear();
            PaneMain.getChildren().add(PaneRecover);
            new FadeInUpTransition(PaneRecover).play();
        }
    }

    @FXML
    public void showSignupPane() {
        if (PaneMain.getChildren().contains(PaneMessage)) {
            PaneMain.getChildren().remove(PaneMessage);
        } else if (PaneMain.getChildren().contains(PaneLogin)) {
            PaneMain.getChildren().remove(PaneLogin);
        } else if (PaneMain.getChildren().contains(PaneRecover)) {
            PaneMain.getChildren().remove(PaneRecover);
        } else if (PaneMain.getChildren().contains(PaneRules)) {
            PaneMain.getChildren().remove(PaneRules);
        } else if (PaneMain.getChildren().contains(PaneFAQ)) {
            PaneMain.getChildren().remove(PaneFAQ);
        }
        if (PaneMain.getChildren().contains(PaneSignup)) {
            PaneMain.getChildren().remove(PaneSignup);
        } else {
            clear();
            PaneMain.getChildren().add(PaneSignup);
            new FadeInUpTransition(PaneSignup).play();
        }
    }

    @FXML
    public void login() throws SQLException, IOException {
        if (FieldUsername.getText().isEmpty() || FieldPassword.getText().isEmpty()) {
            Shared.message_text="Don't leave field blank.";
            Shared.message_type="error";
            showPaneMessage();
        } else if (UsersDB.login(FieldUsername.getText(),FieldPassword.getText())) {
            ScreenController.setScreen(ScreenController.Screen.INTERFACE);
        } else {
            Shared.message_text="Username or password incorrect!\nDon't remember your password? Recover your password!";
            Shared.message_type="error";
            showPaneMessage();
        }
    }

    @FXML
    public void recover() throws SQLException {
        if (FieldEmail.getText().isEmpty() || FieldCharactersRecover.getText().isEmpty()) {
            Shared.message_text="Don't leave field blank.";
            Shared.message_type="error";
            showPaneMessage();
        } else if (!ValidateEmail.validate(FieldEmail.getText())) {
            Shared.message_text = "The email address format is wrong.";
            Shared.message_type = "error";
            showPaneMessage();
        } else if (!FieldCharactersRecover.getText().toLowerCase().equals("wwxht0")) {
            Shared.message_text="The characters you entered are not valid.";
            Shared.message_type="error";
            showPaneMessage();
        } else if (UsersDB.recover(FieldEmail.getText())) {
            Task task=new Task() {
                @Override
                protected Object call() throws Exception {
                    Shared.message_text="A confirmation email has been mailed. Please allow a few minutes\n for the mail to arrive.";
                    Shared.message_type="accept";
                    SendEmailToUser.send(FieldEmail.getText(),"Recover your password","Someone, hopefully you, requested that the password for the account" +
                            " associated with this email address.\n\nYour new password is: "+Long.toHexString(Double.doubleToLongBits(Math.random()))+"\n\nIf you did not do this ignore this email. Please do not reply.");

                    return null;
                }
            };
            task.setOnRunning(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    showPaneMessage();
                }
            });
            new Thread(task).start();
        } else {
            Shared.message_text="The email address was not found in the database.";
            Shared.message_type="error";
            showPaneMessage();
        }
    }

    @FXML void signup() throws SQLException {
        if (FieldRegisterUsername.getText().isEmpty() || FieldRegisterEmail.getText().isEmpty() ||
                FieldRegisterPassword.getText().isEmpty() || FieldRegisterPasswordAgain.getText().isEmpty()
                || FieldCharactersRegister.getText().isEmpty() || ComboCountry.getValue().equals("---- None selected ----")
                || ComboSecretQuestion.getValue().equals("---- None selected ----") ||
                FieldSecretResponse.getText().isEmpty()) {
            Shared.message_text="Don't leave field blank.";
            Shared.message_type="error";
            showPaneMessage();
        } else if (!FieldRegisterPassword.getText().equals(FieldRegisterPasswordAgain.getText())) {
            Shared.message_text="The passwords you entered did not match.";
            Shared.message_type="warn";
            showPaneMessage();
        } else if (!ValidateEmail.validate(FieldRegisterEmail.getText())) {
            Shared.message_text="The email address format is wrong.";
            showPaneMessage();
        } else if (!FieldCharactersRegister.getText().toLowerCase().equals("wwxht0")) {
            Shared.message_text="The characters you entered are not valid.";
            Shared.message_type="error";
            showPaneMessage();
        } else if (UsersDB.signup(new User(FieldRegisterUsername.getText(),FieldRegisterPassword.getText(),
                FieldRegisterEmail.getText(),ButtonFemale.isSelected()?"Female":"Male", ComboCountry.getValue().toString(),ComboSecretQuestion.getValue().toString(), FieldSecretResponse.getText()))) {
            Shared.message_text="Your Account Has Been Created!";
            Shared.message_type="accept";
            showPaneMessage();
        } else {
            Shared.message_text="The username or email already exist.";
            Shared.message_type="error";
            showPaneMessage();
        }
    }

    private void showPaneMessage() {
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
        if (PaneMain.getChildren().contains(PaneLogin)) {
            PaneMain.getChildren().remove(PaneLogin);
        } else if (PaneMain.getChildren().contains(PaneRecover)) {
            PaneMain.getChildren().remove(PaneRecover);
        } else if (PaneMain.getChildren().contains(PaneSignup)) {
            PaneMain.getChildren().remove(PaneSignup);
        }
            PaneMain.getChildren().add(PaneMessage);
    }

    @FXML
    private void showPaneRules() {
        PaneMain.getChildren().remove(PaneSignup);
        PaneMain.getChildren().add(PaneRules);
        TextPosting.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        TextPosting.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        TextPosting.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                GoToWebsite.access("http://www.albinoblacksheep.com/flash/posting");
            }
        });
    }

    @FXML
    private void showPaneFAQ() {
        PaneMain.getChildren().remove(PaneSignup);
        PaneMain.getChildren().add(PaneFAQ);
    }

    private void checkInternetConnection() {
        try {
            if (IP.getIp().equals("null")) {
                try {
                    ScreenController.setScreen(ScreenController.Screen.NOINTERNET);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void clear() {
        FieldUsername.clear();
        FieldPassword.clear();;
        FieldRegisterEmail.clear();
        FieldRegisterPassword.clear();
        FieldRegisterPasswordAgain.clear();
        FieldRegisterUsername.clear();
        FieldCharactersRecover.clear();
        FieldEmail.clear();
        FieldCharactersRegister.clear();
        FieldSecretResponse.clear();
        ComboCountry.setValue("---- None selected ----");
        ComboSecretQuestion.setValue("---- None selected ----");
        ButtonMale.setSelected(false);
        ButtonFemale.setSelected(false);
        CheckAge.setSelected(false);
        CheckFAQ.setSelected(false);
        CheckRules.setSelected(false);
    }
}
