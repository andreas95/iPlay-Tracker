package main.java.com.andreas.tracker.iplay.database;

import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.model.Poll;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 7/5/16.
 */
public class PollDB {

    public static void addPoll(Poll poll) throws SQLException {
        String SQL="INSERT INTO poll (question) VALUES (?)";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setString(1, poll.getQuestion());
        myStatement.executeUpdate();
        SQL="INSERT INTO poll_answers (answer,poll_id) VALUES (?,?)";
        for (int i=0; i<poll.getAnswers().size(); i++) {
            myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, poll.getAnswers().get(i));
            myStatement.setInt(2, getLastPollId());
            myStatement.executeUpdate();
        }
        myStatement.close();
    }

    public static void editPoll(Poll poll, String old_title) throws SQLException {
        //update question
        String sql="update poll set question=? where question=?";
        PreparedStatement myStmt=ConnectDB.getConnection().prepareStatement(sql);
        myStmt.setString(1, poll.getQuestion());
        myStmt.setString(2, old_title);
        myStmt.executeUpdate();

        //delete all answers
        sql = "delete from poll_answers where poll_id=?";
        myStmt = ConnectDB.getConnection().prepareStatement(sql);
        myStmt.setInt(1, getPollId(poll.getQuestion()));
        myStmt.executeUpdate();

        //insert answers
        sql="INSERT INTO poll_answers (answer,poll_id) VALUES (?,?)";
        for (int i=0; i<poll.getAnswers().size(); i++) {
            myStmt=ConnectDB.getConnection().prepareStatement(sql);
            myStmt.setString(1, poll.getAnswers().get(i));
            myStmt.setInt(2, getPollId(poll.getQuestion()));
            myStmt.executeUpdate();
        }
        myStmt.close();
    }

    public static void deletePoll(String title) throws SQLException {
        //delete all respondents
        String sql = "delete from poll_respondent where poll_id=?";
        PreparedStatement myStmt = ConnectDB.getConnection().prepareStatement(sql);
        myStmt.setInt(1, getPollId(title));
        myStmt.executeUpdate();
        //delete all answers
        sql = "delete from poll_answers where poll_id=?";
        myStmt = ConnectDB.getConnection().prepareStatement(sql);
        myStmt.setInt(1, getPollId(title));
        myStmt.executeUpdate();
        //delete poll
        sql="delete from poll where id_poll=?";
        myStmt = ConnectDB.getConnection().prepareStatement(sql);
        myStmt.setInt(1, getPollId(title));
        myStmt.executeUpdate();
    }

    public static int getLastPollId() throws SQLException {
        Statement myStm=ConnectDB.getConnection().createStatement();
        ResultSet myRs=myStm.executeQuery("select * from poll order by id_poll DESC LIMIT 1");
        while (myRs.next()){
            return myRs.getInt("id_poll");
        }
        return 0;
    }

    public static List<String> getTitlesPoll() throws SQLException {
        Statement myStm=ConnectDB.getConnection().createStatement();
        ResultSet myRs=myStm.executeQuery("select * from poll order by id_poll DESC");
        List<String> title=new ArrayList<String>();
        while (myRs.next())
            title.add(myRs.getString("question"));
        return title;
    }

    public static Poll getLastPoll() throws SQLException {
        Poll poll=new Poll();
        Statement myStm=ConnectDB.getConnection().createStatement();
        ResultSet myRs=myStm.executeQuery("select * from poll order by id_poll DESC LIMIT 1");
        if (myRs.next()){
             poll.setQuestion(myRs.getString("question"));
            poll.setNo_votes(myRs.getInt("no_votes"));
        }
        List<String> list=new ArrayList<String>();
        List<Integer> list2=new ArrayList<>();
        myRs=myStm.executeQuery("select * from poll_answers where poll_id="+getPollId(poll.getQuestion()));
        while (myRs.next()) {
            list.add(myRs.getString("answer"));
            list2.add(myRs.getInt("no_votes"));
        }
        poll.setAnswers(list);
        poll.setAnswers_votes(list2);
        return poll;
    }

    public static Poll getPoll(String title) throws  SQLException {
        List<String> list=new ArrayList<String>();
        Statement myStm=ConnectDB.getConnection().createStatement();
        ResultSet myRs=myStm.executeQuery("select * from poll_answers where poll_id="+getPollId(title));
        while (myRs.next()) {
            list.add(myRs.getString("answer"));
        }
        return new Poll(title,list);
    }

    public static int getPollId(String title) throws SQLException {
        Statement myStm=ConnectDB.getConnection().createStatement();
        ResultSet myRs=myStm.executeQuery("select * from poll where question='"+title+"';");
        if (myRs.next()){
            return myRs.getInt("id_poll");
        }
        return -1;
    }

    public static int getPollAnswerID(String answer) throws SQLException {
        String SQL="SELECT * FROM poll_answers where answer=? and poll_id=?;";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setString(1, answer);
        myStatement.setInt(2, getLastPollId());
        ResultSet myResultSet=myStatement.executeQuery();
        if (myResultSet.next()) {
           return myResultSet.getInt("idpoll_answers");
        }
        return -1;
    }

    public static void votePoll(String answer) throws SQLException {
        int no_votes=0;
        String SQL="INSERT INTO poll_respondent (user_id, poll_id, idpoll_answers) VALUES (?,?,?);";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, UsersDB.getUserID(Shared.user));
        myStatement.setInt(2, getLastPollId());
        myStatement.setInt(3, getPollAnswerID(answer));
        myStatement.executeUpdate();
        SQL="UPDATE poll_answers set no_votes=? where idpoll_answers=?";
        ResultSet myResultSet=myStatement.executeQuery("select count(*) as no_votes from poll_respondent where idpoll_answers="+getPollAnswerID(answer));
        if (myResultSet.next()) {
            no_votes = myResultSet.getInt("no_votes");
        }
        myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, no_votes);
        myStatement.setInt(2, getPollAnswerID(answer));
        myStatement.executeUpdate();
        SQL="UPDATE poll set no_votes=? where id_poll=?";
        myResultSet=myStatement.executeQuery("select sum(no_votes) as no_votes from poll_answers where poll_id="+getLastPollId());
        if (myResultSet.next()) {
            no_votes = myResultSet.getInt("no_votes");
        } else {
            no_votes=0;
        }
        myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, no_votes);
        myStatement.setInt(2, getLastPollId());
        myStatement.executeUpdate();
        myStatement.close();
    }

}
