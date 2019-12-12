package main.java.com.andreas.tracker.iplay.fragments;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import main.java.com.andreas.tracker.iplay.common.Shared;

/**
 * Created by Andreas on 8/10/16.
 */
public class ViewTicketFragment {

    @FXML
    private Text TextTicketSubject;
    @FXML
    private Text TextMessage;
    @FXML
    private Text TextAnswer;

    public void initialize() {
        TextTicketSubject.setText("My ticket #"+ Shared.view_ticket.getSubject());
        TextMessage.setText(Shared.view_ticket.getMessage());
        TextAnswer.setText(Shared.view_ticket.getAnswer());
    }
}
