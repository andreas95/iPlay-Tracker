package main.java.com.andreas.tracker.iplay.database;

import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by Andreas on 6/22/16.
 */
public class UsersDB {
    private static Date data = new Date();

    public static boolean login(String username,String password) throws SQLException {
            String SQL="SELECT * FROM users WHERE username=? AND password=MD5(?);";
            PreparedStatement myStmt=ConnectDB.getConnection().prepareStatement(SQL);
            myStmt.setString(1, username);
            myStmt.setString(2, password);
            ResultSet myRs=myStmt.executeQuery();
            if (myRs.next()){
                //set user details
                Shared.user=username;
                Shared.type=myRs.getString("type");
                Shared.download_active=myRs.getInt("download_active");
                Shared.upload_active=myRs.getInt("upload_active");
                Shared.uploaded=myRs.getFloat("uploaded");
                Shared.downloaded=myRs.getFloat("downloaded");
                Shared.invites=myRs.getInt("invites");
                Shared.inbox=myRs.getInt("inbox");
                Shared.sentbox=myRs.getInt("sentbox");
                Shared.ratio=myRs.getFloat("ratio");
                Shared.vote_last_poll=voteLastPoll();
                Shared.join_date=myRs.getString("join_date");

                //set last seen date
                SQL="UPDATE users set last_seen=? where username=?";
                myStmt=ConnectDB.getConnection().prepareStatement(SQL);
                myStmt.setString(1, String.format("%tA, %<tB %<td, %<tY", data));
                myStmt.setString(2, Shared.user);
                myStmt.executeUpdate();
                return true;
            }
        return false;
    }

    public static boolean voteLastPoll() throws SQLException {
        String SQL="SELECT * FROM poll_respondent where user_id=? and poll_id=?;";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, getUserID(Shared.user));
        myStatement.setInt(2, PollDB.getLastPollId());
        ResultSet myResultSet=myStatement.executeQuery();
        if (myResultSet.next()) {
            return true;
        }
        return false;
    }

    public static boolean recover(String email) throws SQLException{
            String SQL="SELECT * FROM  users WHERE email=?;";
            PreparedStatement myStmt=ConnectDB.getConnection().prepareStatement(SQL);
            myStmt.setString(1, email);
            ResultSet myRs=myStmt.executeQuery();
            if (myRs.next()) {
                if (myRs.getString("email").equals(email)) {
                    return true;
                }
            }
        return false;
    }

    public static boolean signup(User user) {
            String SQL="INSERT INTO users (username,password,email,gender,country,secret_question,responde_secret_question,join_date) VALUES(?,MD5(?),?,?,?,?,?,?);";
        PreparedStatement myStatement= null;
        try {
            myStatement = ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, user.getUsername());
            myStatement.setString(2, user.getPassword());
            myStatement.setString(3, user.getEmail());
            myStatement.setString(4, user.getGender());
            myStatement.setString(5, user.getCountry());
            myStatement.setString(6, user.getSecret_question());
            myStatement.setString(7, user.getResponse_secret_question());
            myStatement.setString(8, String.format("%tA, %<tB %<td, %<tY", data));
            if (myStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public static String lastUser() throws SQLException{
        String SQL="SELECT username FROM users order by id desc LIMIT 1;";
        Statement myStatement=ConnectDB.getConnection().createStatement();
        ResultSet myResultSet=myStatement.executeQuery(SQL);
        if (myResultSet.next()) {
            return myResultSet.getString("username");
        }
        return "";
    }

    public static int getUserID(String username) throws SQLException {
        String SQL="SELECT * FROM users where username=?;";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setString(1, username);
        ResultSet myResultSet=myStatement.executeQuery();
        if (myResultSet.next()) {
            return myResultSet.getInt("id");
        }
        return -1;
    }

    public static User getUser(String username) throws SQLException {
        String SQL="SELECT * FROM users where username=?;";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setString(1, username);
        ResultSet myResultSet=myStatement.executeQuery();
        if (myResultSet.next()) {
            return new User(username, myResultSet.getString("type"), myResultSet.getString("join_date"), myResultSet.getString("last_seen"),
                    myResultSet.getString("email"), myResultSet.getFloat("uploaded"), myResultSet.getFloat("downloaded"),
                    myResultSet.getFloat("ratio"), myResultSet.getInt("invites"), myResultSet.getString("invited_by"),
                    myResultSet.getInt("number_comments"), myResultSet.getInt("number_posts"), myResultSet.getInt("number_thanks"),
                    myResultSet.getString("country"));
            }
        return new User();
    }
}
