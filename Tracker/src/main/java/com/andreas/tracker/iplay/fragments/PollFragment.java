package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.PollDB;
import main.java.com.andreas.tracker.iplay.model.Poll;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 7/5/16.
 */
public class PollFragment {

    @FXML
    private VBox PanePoll;
    @FXML
    private VBox PaneAdd;
    @FXML
    private VBox PaneEdit;
    @FXML
    private VBox PaneDelete;
    @FXML
    private Button ButtonAddAnswer;
    @FXML
    private Button ButtonCancel;
    @FXML
    private Button ButtonSavePoll;
    @FXML
    private GridPane PaneAddAnswers;
    @FXML
    private TextField FieldAnswer1;
    @FXML
    private TextField FieldQuestion;
    @FXML
    private ComboBox ListPolls;
    @FXML
    private GridPane PaneEditScene1;
    @FXML
    private GridPane PaneEditScene2;
    @FXML
    private TextField FieldQuestionEdit;
    @FXML
    private Button ButtonAddAnswerEdit;
    @FXML
    private GridPane PaneAddAnswersEdit;
    @FXML
    private Button ButtonCancelEdit;
    @FXML
    private Button ButtonEditPoll;
    @FXML
    private ComboBox ListPollDelete;
    @FXML
    private Button ButtonNextEdit;
    @FXML
    private Button ButtonCancelDelete;
    @FXML
    private Button ButtonDeletePoll;
    private List<TextField> ListAnswersPollEdit=new ArrayList<TextField>();
    private int answer_no=0;
    private int answer_no_edit=0;
    private List<TextField> listFieldAnswer=new ArrayList<TextField>();

    public void initialize() throws SQLException {
        PaneEdit.getChildren().remove(PaneEditScene2);

        //add first answer to listFieldAnswer
        listFieldAnswer.add(FieldAnswer1);

        //initialize ComboBox with questions list
        ListPolls.getItems().addAll(PollDB.getTitlesPoll());
        ListPollDelete.getItems().addAll(PollDB.getTitlesPoll());


        PanePoll.getChildren().removeAll(PaneAdd,PaneEdit,PaneDelete);
        switch (Shared.poll_pane) {
            case "Add":
                PanePoll.getChildren().add(PaneAdd);
                break;
            case "Edit":
                PanePoll.getChildren().add(PaneEdit);
                break;
            case "Delete":
                PanePoll.getChildren().add(PaneDelete);
                break;
        }
    }

    @FXML
    public void cancel() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.HOME);
    }

    @FXML
    public void edit() throws SQLException {
        if (!ListPolls.getValue().toString().equals("---- None selected ----")) {
            Poll poll = PollDB.getPoll(ListPolls.getValue().toString());
            PaneEdit.getChildren().remove(PaneEditScene1);
            FieldQuestionEdit.setText(poll.getQuestion());
            for (String answerField : poll.getAnswers()) {
                Text answer = new Text("Answer:");
                answer.setFill(Color.ALICEBLUE);
                answer.setFont(Font.font("Adobe Hebrew Bold", 14));
                TextField fieldAnswer = new TextField();
                fieldAnswer.setText(answerField);
                fieldAnswer.minWidth(300);
                ListAnswersPollEdit.add(fieldAnswer);
                PaneAddAnswersEdit.add(answer, 0, answer_no_edit);
                PaneAddAnswersEdit.add(fieldAnswer, 1, answer_no_edit);
                answer_no_edit++;
            }
            PaneEdit.getChildren().add(PaneEditScene2);
        }
    }

    @FXML
    public void delete() throws IOException, SQLException {
        if (!ListPollDelete.getValue().toString().equals("---- None selected ----")) {
            PollDB.deletePoll(ListPollDelete.getValue().toString());
            Shared.message_text="Success";
            Shared.message_type="accept";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        }
    }

    @FXML
    public void editPoll() throws SQLException, IOException {
        List<String> list=new ArrayList<String>();
        for (TextField textField: ListAnswersPollEdit) {
            if (!textField.getText().equals("")) {
                list.add(textField.getText());
            }
        }
        PollDB.editPoll(new Poll(FieldQuestionEdit.getText(),list), ListPolls.getValue().toString());
        Shared.message_text="Success";
        Shared.message_type="accept";
        ScreenController.setScreen(ScreenController.Screen.MESSAGE);
    }

    @FXML
    public void savePoll() throws SQLException, IOException {
        List<String> answers=new ArrayList<String>();
        for (TextField textField: listFieldAnswer) {
            if (!textField.getText().equals("")) {
                answers.add(textField.getText());
            }
        }
        PollDB.addPoll(new Poll(FieldQuestion.getText(),answers));
        Shared.vote_last_poll=false;
        Shared.message_text="Success";
        Shared.message_type="accept";
        ScreenController.setScreen(ScreenController.Screen.MESSAGE);
    }

    @FXML
    public void addAnswer() {
        answer_no++;
        Text answer=new Text("Answer:");
        answer.setFill(Color.ALICEBLUE);
        answer.setFont(Font.font("Adobe Hebrew Bold",14));
        TextField fieldAnswer=new TextField();
        fieldAnswer.setPromptText("Type your answer here");
        fieldAnswer.minWidth(300);
        PaneAddAnswers.add(answer,0,answer_no);
        PaneAddAnswers.add(fieldAnswer,1,answer_no);
        listFieldAnswer.add(fieldAnswer);
    }

    @FXML
    public void addAnswerEdit() {
        Text answer=new Text("Answer:");
        answer.setFill(Color.ALICEBLUE);
        answer.setFont(Font.font("Adobe Hebrew Bold",14));
        TextField fieldAnswer=new TextField();
        fieldAnswer.setPromptText("Type your answer here");
        fieldAnswer.minWidth(300);
        PaneAddAnswersEdit.add(answer,0,answer_no_edit);
        PaneAddAnswersEdit.add(fieldAnswer,1,answer_no_edit);
        ListAnswersPollEdit.add(fieldAnswer);
        answer_no_edit++;
    }
}
