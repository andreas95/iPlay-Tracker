package main.java.com.andreas.tracker.iplay.database;

import main.java.com.andreas.tracker.iplay.model.Comment;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Andreas on 8/20/16.
 */
public class CommentsDB {

    public static boolean addComment(Comment comm) {
        String SQL="insert into comments (user_id,torrent_id,added,text,ori_text) " +
                "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setInt(1, UsersDB.getUserID(comm.getUser()));
            myStatement.setInt(2, TorrentsDB.getTorrentID(comm.getTorrent()));
            myStatement.setString(3, comm.getAdded());
            myStatement.setString(4, comm.getText());
            myStatement.setString(5, comm.getText());
            myStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean editComment(Comment comm) {
        String SQL="update comments set text=?, ori_text=?, editedby=?, editedat=? where" +
                "user_id=? and torrent_id=? and added=?";
        try {
            PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, comm.getText());
            myStatement.setString(2, comm.getOri_text());
            myStatement.setInt(3, UsersDB.getUserID(comm.getEdited_by()));
            myStatement.setString(4, comm.getEdited_at());
            myStatement.setInt(5, UsersDB.getUserID(comm.getUser()));
            myStatement.setInt(6, TorrentsDB.getTorrentID(comm.getTorrent()));
            myStatement.setString(7, comm.getAdded());
            myStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

/*    public static int getCommentsNo() {
        String SQL="";
        try {
            return 1;
        } catch (SQLException e) {
            return -1;
        }
    }

    public static List<Comment> getCommentsList() {
        String SQL="";
        try {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }*/

}
