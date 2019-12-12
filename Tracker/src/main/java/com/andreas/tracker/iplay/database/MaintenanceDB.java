package main.java.com.andreas.tracker.iplay.database;

import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.model.Ticket;
import main.java.com.andreas.tracker.iplay.model.Version;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andreas on 8/7/16.
 */
public class MaintenanceDB {

    public static boolean send_uploader_application(String internalSpeed, String externalSpeed, String releases, String experience) {
        String SQL="INSERT INTO uploader_application (userid,internal_speed,external_speed,releases,experience,added) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setInt(1, UsersDB.getUserID(Shared.user));
            myStatement.setString(2, internalSpeed);
            myStatement.setString(3, externalSpeed);
            myStatement.setString(4, releases);
            myStatement.setString(5, experience);
            Date data = new Date();
            myStatement.setString(6, String.format("%tA, %<tB %<td, %<tY %<tr", data));
            myStatement.executeUpdate();
            myStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void view_uploader_application() {

    }

    public static boolean send_ticket(Ticket ticket) {
        String SQL="INSERT INTO tickets (iduser,category,subject,message,added) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setInt(1, UsersDB.getUserID(Shared.user));
            myStatement.setString(2, ticket.getCategory());
            myStatement.setString(3, ticket.getSubject());
            myStatement.setString(4, ticket.getMessage());
            Date data = new Date();
            myStatement.setString(5, String.format("%tA, %<tB %<td, %<tY %<tr", data));
            myStatement.executeUpdate();
            myStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Ticket> view_ticket() {
        String SQL="SELECT * FROM tickets where iduser=? order by idtickets DESC;";
        List<Ticket> list=new ArrayList<>();
        try {
            PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setInt(1, UsersDB.getUserID(Shared.user));
            ResultSet myResultSet=myStatement.executeQuery();
            while (myResultSet.next()) {
                list.add(new Ticket(myResultSet.getString("category"), myResultSet.getString("subject"),
                        myResultSet.getString("message"), myResultSet.getString("added"), myResultSet.getString("solved"),
                        myResultSet.getString("done_by"), myResultSet.getString("done_in")));
            }
            return list;
        } catch (SQLException e) {
            return list;
        }
    }

    public static Ticket getTicket(String subject) {
        String SQL="SELECT * FROM tickets where iduser=? and subject=?";
        try {
            PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setInt(1, UsersDB.getUserID(Shared.user));
            myStatement.setString(2, subject);
            ResultSet myResultSet=myStatement.executeQuery();
            if (myResultSet.next()) {
                return new Ticket(subject, myResultSet.getString("message"), myResultSet.getString("answer"), null);
            }
             return new Ticket();
        } catch (SQLException e) {
            return new Ticket();
        }
    }

    public static Version getLastVersion() {
        String SQL="SELECT * from maintenance order by id desc limit 1";
        try {
            Statement myStatement=ConnectDB.getConnection().createStatement();
            ResultSet myResultSet=myStatement.executeQuery(SQL);
            if (myResultSet.next()) {
                return new Version(myResultSet.getString("version"), myResultSet.getString("news"));
            }
            return new Version();
        } catch (SQLException e) {
            return new Version();
        }
    }

}
