package main.java.com.andreas.tracker.iplay.fragments;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.NewsDB;
import main.java.com.andreas.tracker.iplay.database.PollDB;
import main.java.com.andreas.tracker.iplay.database.UsersDB;
import main.java.com.andreas.tracker.iplay.model.News;
import main.java.com.andreas.tracker.iplay.model.Poll;
import org.w3c.dom.Document;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 6/22/16.
 */
public class HomeFragment {

    @FXML
    private Text LastUser;
    @FXML
    private Text TextDate;
    @FXML
    private Text TextTitle;
    @FXML
    private VBox PaneNews;
    @FXML
    private HBox ButtonGroup;
    @FXML
    private Button ButtonAddNews;
    @FXML
    private Button ButtonEditNews;
    @FXML
    private Button ButtonDeleteNews;
    @FXML
    private WebView NewsContent;
    @FXML
    private Label PreviousPoll;
    @FXML
    private Label Suggestions;
    @FXML
    private Text TextPollTitle;
    @FXML
    private Text TextVotes;
    @FXML
    private VBox PollData;
    @FXML
    private HBox ButtonGroupPoll;
    @FXML
    private VBox PanePoll;
    @FXML
    private Button ButtonAddPoll;
    @FXML
    private Button ButtonEditPoll;
    @FXML
    private Button ButtonDeletePoll;
    @FXML
    private VBox PanePollResult;
    @FXML
    private VBox PanePollVote;
    @FXML
    private VBox PanePollEmpty;
    @FXML
    private Text TextPollTitleVote;
    @FXML
    private GridPane PollDataVote;
    @FXML
    private HBox PanePollBottom;
    private List<RadioButton> ListVoteAnswers=new ArrayList<RadioButton>();

    public void initialize() throws SQLException {
        LastUser.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Shared.view_profile= UsersDB.getUser(LastUser.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    ScreenController.setScreen(ScreenController.Screen.VIEWPROFILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        //set poll
        PanePoll.getChildren().removeAll(PanePollEmpty,PanePollResult,PanePollVote,PanePollBottom);
        Poll lastPoll= PollDB.getLastPoll();
        if (lastPoll.getQuestion()==null) {
            PanePoll.getChildren().add(PanePollEmpty);
        } else if (Shared.vote_last_poll) {
            TextVotes.setText(String.valueOf(lastPoll.getNo_votes()));
            TextPollTitle.setText(lastPoll.getQuestion());
            PanePoll.getChildren().addAll(PanePollResult,PanePollBottom);
            int poll_vote_no=0;
            for (String answer: lastPoll.getAnswers()) {
                VBox PollQuestion=new VBox(20);
                PollQuestion.setAlignment(Pos.CENTER);
                GridPane total=new GridPane();
                total.setVgap(8);
                total.setHgap(8);
                total.setAlignment(Pos.CENTER);
                float percentage=100*lastPoll.getAnswers_votes().get(poll_vote_no)/lastPoll.getNo_votes();
                Text question=new Text((int)percentage+"% - "+answer);
                question.setFill(Color.ALICEBLUE);
                ProgressBar pb=new ProgressBar();
                pb.setPrefWidth(300);
                pb.setProgress(percentage/100);
                total.add(question,0,poll_vote_no);
                total.add(pb,0,poll_vote_no+1);
                PollQuestion.getChildren().add(total);
                PollData.getChildren().add(PollQuestion);
                poll_vote_no++;
            }
        } else {
            TextPollTitleVote.setText(lastPoll.getQuestion());
            final ToggleGroup group = new ToggleGroup();
            int poll_vote_no=0;
            for (String answer: lastPoll.getAnswers()) {
                RadioButton rb = new RadioButton(answer);
                rb.setToggleGroup(group);
                rb.setSelected(false);
                rb.setTextFill(Color.ALICEBLUE);
                rb.getStyleClass().add("red-radio-button");
                PollDataVote.add(rb,0,poll_vote_no);
                poll_vote_no++;
                ListVoteAnswers.add(rb);
            }
            PanePoll.getChildren().addAll(PanePollVote,PanePollBottom);
        }

/*        //hide scroll for webview (!!!)
        NewsContent.getChildrenUnmodifiable().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                Set<Node> deadSeaScrolls=NewsContent.lookupAll(".scroll-bar");
                for (Node scroll: deadSeaScrolls) {
                    scroll.setVisible(false);
                }
            }
        });*/

        LastUser.setText(UsersDB.lastUser());

        News lastNews= NewsDB.getLastNews();
        TextDate.setText(lastNews.getDate());
        TextTitle.setText(lastNews.getTitle());
        WebEngine webEngine = NewsContent.getEngine();
        if (lastNews.getTitle()==null || lastNews.getTitle().isEmpty()) {
            webEngine.loadContent("<body style=\"background-color: #161616;\" contenteditable=\"false\"><font face=\"Segoe UI\" color=\"#ffffff\">None news into database.</font></body>");
        } else {
            webEngine.loadContent(lastNews.getContent());
        }
        webEngine.documentProperty().addListener(new ChangeListener<Document>() {
            @Override public void changed(ObservableValue<? extends Document> prop, Document oldDoc, Document newDoc) {
                String heightText = NewsContent.getEngine().executeScript(
                        "window.getComputedStyle(document.body, null).getPropertyValue('height')"
                ).toString();
                double height = Double.valueOf(heightText.replace("px", ""));

                NewsContent.setPrefHeight(height+20);
            }
        });

        if (!Shared.type.equals("SysOp")) {
            PaneNews.getChildren().remove(ButtonGroup);
            PanePoll.getChildren().remove(ButtonGroupPoll);
        }
    }

    @FXML
    public void addNews() throws IOException {
        Shared.news_pane="Add";
        ScreenController.setScreen(ScreenController.Screen.NEWS);
    }

    @FXML
    public void editNews() throws IOException{
        Shared.news_pane="Edit";
        ScreenController.setScreen(ScreenController.Screen.NEWS);
    }

    @FXML
    public void deleteNews() throws IOException{
        Shared.news_pane="Delete";
        ScreenController.setScreen(ScreenController.Screen.NEWS);
    }

    @FXML
    public void addPoll() throws IOException {
        Shared.poll_pane="Add";
        ScreenController.setScreen(ScreenController.Screen.POLL);
    }

    @FXML
    public void deletePoll() throws IOException {
        Shared.poll_pane="Delete";
        ScreenController.setScreen(ScreenController.Screen.POLL);
    }

    @FXML
    public void editPoll() throws IOException {
        Shared.poll_pane="Edit";
        ScreenController.setScreen(ScreenController.Screen.POLL);
    }

    @FXML
    public void vote() throws SQLException, IOException {
        for (RadioButton rb : ListVoteAnswers) {
            if (rb.isSelected()) {
                PollDB.votePoll(rb.getText().toString());
                Shared.vote_last_poll=true;
                ScreenController.setScreen(ScreenController.Screen.HOME);
            }
        }
    }
}
