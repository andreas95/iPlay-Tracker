package main.java.com.andreas.tracker.iplay.fragments;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.ScreenController;
import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.database.MaintenanceDB;
import main.java.com.andreas.tracker.iplay.model.Ticket;

import java.io.IOException;
import java.util.List;

import static main.java.com.andreas.tracker.iplay.common.StageManager.getStage;

/**
 * Created by Andreas on 6/22/16.
 */
public class StaffFragment {

    @FXML
    private VBox PaneMain;
    @FXML
    private VBox PaneTickets;
    @FXML
    private VBox PaneNewTicket;
    @FXML
    private VBox PaneViewTickets;
    @FXML
    private Button ButtonNewTicket;
    @FXML
    private Button ButtonViewTickets;
    @FXML
    private ComboBox ComboTicketCategory;
    @FXML
    private TextField FieldSubject;
    @FXML
    private TextArea FieldMessage;
    @FXML
    private Button ButtonCancelTicket;
    @FXML
    private Button ButtonSendTicket;
    @FXML
    private GridPane PaneMyTickets;


    public void initialize() {
        PaneMain.getChildren().removeAll(PaneNewTicket,PaneViewTickets);
        //set mouse pointers
        ButtonNewTicket.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonNewTicket.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
        ButtonViewTickets.setOnMouseEntered(e->getStage().getScene().setCursor(Cursor.HAND));
        ButtonViewTickets.setOnMouseExited(e->getStage().getScene().setCursor(Cursor.DEFAULT));
    }

    @FXML
    public void view_my_tickets() throws IOException {
        List<Ticket> listTickets=MaintenanceDB.view_ticket();
        if (listTickets.isEmpty() || listTickets==null) {
            Shared.message_type = "error";
            Shared.message_text = "You don't have tickets.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            PaneMain.getChildren().remove(PaneTickets);
            PaneMain.getChildren().add(PaneViewTickets);
            int index_row=1;
            for (Ticket tick : listTickets) {
                HBox box=new HBox();
                box.setAlignment(Pos.CENTER);
                box.setPrefWidth(50);
                box.getStyleClass().add("dltable-m1");
                PaneMyTickets.add(box,0,index_row);
                HBox box2=new HBox();
                box2.setAlignment(Pos.CENTER);
                box2.getStyleClass().add("dltable-m2");
                Text text_subject=new Text(tick.getSubject());
                text_subject.getStyleClass().add("link");
                text_subject.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        Shared.view_ticket=MaintenanceDB.getTicket(text_subject.getText());
                        try {
                            ScreenController.setScreen(ScreenController.Screen.VIEWTICKET);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                box2.getChildren().add(text_subject);
                HBox.setMargin(text_subject,new Insets(0,20,20,0));
                PaneMyTickets.add(box2,1,index_row);
                HBox box3=new HBox();
                box3.setAlignment(Pos.CENTER);
                box3.getStyleClass().add("dltable-m2");
                Text text_sender=new Text(Shared.user);
                text_sender.setFill(Color.WHITE);
                text_sender.setStyle("-fx-font-weight:bold;");
                box3.getChildren().add(text_sender);
                HBox.setMargin(text_sender,new Insets(0,20,20,0));
                PaneMyTickets.add(box3,2,index_row);
                HBox box4=new HBox();
                box4.setAlignment(Pos.CENTER);
                box4.getStyleClass().add("dltable-m2");
                Text text_category=new Text(tick.getCategory());
                text_category.setFill(Color.WHITE);
                box4.getChildren().add(text_category);
                HBox.setMargin(text_category,new Insets(0,20,20,0));
                PaneMyTickets.add(box4,3,index_row);
                HBox box5=new HBox();
                box5.setAlignment(Pos.CENTER);
                box5.getStyleClass().add("dltable-m2");
                Text text_added=new Text(tick.getAdded());
                text_added.setFill(Color.WHITE);
                box5.getChildren().add(text_added);
                HBox.setMargin(text_added,new Insets(0,20,20,0));
                PaneMyTickets.add(box5,4,index_row);
                HBox box6=new HBox();
                box6.setAlignment(Pos.CENTER);
                box6.getStyleClass().add("dltable-m2");
                ImageView solved=new ImageView();
                if (tick.getSolved().equals("Yes")) {
                    solved.setImage(Shared.img_solved_yes);
                } else {
                    solved.setImage(Shared.img_solved_no);
                }
                HBox.setMargin(solved,new Insets(0,20,20,0));
                box6.getChildren().add(solved);
                PaneMyTickets.add(box6,5,index_row);
                HBox box7=new HBox();
                box7.setAlignment(Pos.CENTER);
                box7.getStyleClass().add("dltable-m2");
                Text text_done_by=new Text(tick.getDone_by());
                text_done_by.setFill(Color.WHITE);
                box7.getChildren().add(text_done_by);
                HBox.setMargin(text_done_by,new Insets(0,20,20,0));
                PaneMyTickets.add(box7,6,index_row);
                HBox box8=new HBox();
                box8.setAlignment(Pos.CENTER);
                box8.getStyleClass().add("dltable-m2");
                Text text_done_in=new Text(tick.getDone_in());
                text_done_in.setFill(Color.WHITE);
                box8.getChildren().add(text_done_in);
                HBox.setMargin(text_done_in,new Insets(0,20,20,0));
                PaneMyTickets.add(box8,7,index_row);
                HBox box9=new HBox();
                box9.setAlignment(Pos.CENTER);
                box9.prefWidth(50);
                box9.getStyleClass().add("dltable-m4");
                PaneMyTickets.add(box9,8,index_row);
                index_row++;
            }
            for (int i=0; i<9;i++) {
                HBox box=new HBox();
                box.setAlignment(Pos.CENTER);
                box.minHeight(10);
                if (i==0) {
                    box.prefWidth(50);
                    box.getStyleClass().add("dltable-f1");
                } else if (i==8) {
                    box.prefWidth(50);
                    box.getStyleClass().add("dltable-f4");
                } else {
                    box.getStyleClass().add("dltable-f2");
                }
                PaneMyTickets.add(box,i,index_row);
            }
        }
    }

    @FXML
    public void new_ticket() {
        PaneMain.getChildren().remove(PaneTickets);
        PaneMain.getChildren().add(PaneNewTicket);
        ComboTicketCategory.getItems().addAll("General questions", "Donations", "Forums", "Uploading", "Bugs", "Others");
    }

    @FXML
    public void send_ticket() throws IOException {
        if (FieldMessage.getText().isEmpty() || FieldSubject.getText().isEmpty() || ComboTicketCategory.getValue().equals("None")) {
            Shared.message_type = "error";
            Shared.message_text = "Don't leave field blank.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else if (MaintenanceDB.send_ticket(new Ticket(ComboTicketCategory.getValue().toString(), FieldSubject.getText(),FieldMessage.getText()))) {
            Shared.message_type = "accept";
            Shared.message_text = "The ticket are sent.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        } else {
            Shared.message_type = "error";
            Shared.message_text = "Server error.";
            ScreenController.setScreen(ScreenController.Screen.MESSAGE);
        }
    }

    @FXML
    public void cancel() throws IOException {
        ScreenController.setScreen(ScreenController.Screen.STAFF);
    }

}
