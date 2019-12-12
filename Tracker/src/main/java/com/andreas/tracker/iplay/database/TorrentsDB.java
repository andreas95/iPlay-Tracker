package main.java.com.andreas.tracker.iplay.database;

import main.java.com.andreas.tracker.iplay.common.Shared;
import main.java.com.andreas.tracker.iplay.model.Torrent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreas on 7/14/16.
 */
public class TorrentsDB {

    public static boolean addTorrent(Torrent torrent) {
        String SQL="INSERT INTO torrents (type, name, genre, tags, link1, link2, description, added, size)" +
                " VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement myStatement = ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, torrent.getType());
            myStatement.setString(2, torrent.getName());
            myStatement.setString(3, torrent.getGenre());
            myStatement.setString(4, torrent.getTags());
            myStatement.setString(5, torrent.getLink1());
            myStatement.setString(6, torrent.getLink2());
            myStatement.setString(7, torrent.getDescription());
            myStatement.setString(8, torrent.getAdded());
            myStatement.setString(9, torrent.getSize());
            myStatement.executeUpdate();
            myStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void editTorrent() throws SQLException {

    }

    public static void deleteTorrent() throws SQLException {

    }

    public static Torrent getTorrent(String name) throws SQLException {
        String SQL="SELECT * FROM torrents where name='"+name+"';";
        Statement myStatement=ConnectDB.getConnection().createStatement();
        ResultSet myResultSet=myStatement.executeQuery(SQL);
        if  (myResultSet.next()) {
           return new Torrent(myResultSet.getString("type"), myResultSet.getString("name"),myResultSet.getString("genre"), myResultSet.getString("tags"),
                   myResultSet.getString("link1"), myResultSet.getString("link2"), myResultSet.getInt("no_comments"),
                   myResultSet.getString("added"), myResultSet.getString("size"), myResultSet.getInt("snatched"), myResultSet.getInt("seeds"),
                   myResultSet.getInt("leechs"),myResultSet.getString("uploader"), myResultSet.getString("description"),myResultSet.getString("thanks"));
        } else  {
            return new Torrent();
        }
    }

    public static List<Torrent> getTorrentsList(String type, String limit, String search, String in, String status, List<String> categories) {
        String SQL="select * from torrents where ";
        if (search.isEmpty()) {
            SQL+="1=1 ";
        } else {
            SQL+=in+" like '%"+search+"%' ";
        }
        switch (status) {
            case "All":
                break;
            case "Active":
                SQL+="AND seeds>0 ";
                break;
            case "Dead":
                SQL+="AND seeds=0";
                break;
            default:
                break;
        }
        for (int i=0;i<categories.size();i++) {
            SQL+=" AND type='"+categories.get(i)+"'";
        }
        switch (type) {
            case "Adult":
                SQL+=" AND type in ('Adult')";
                break;
            case "Music":
                SQL+=" AND type in ('Music')";
                break;
            case "Docs":
                SQL+=" AND type in ('Docs')";
                break;
            case "Browse":
                SQL+=" AND type not in ('Adult','Music','Docs')";
                break;
            default:
                break;
        }
        SQL+=" order by idtorrents DESC LIMIT ?,?";
        String[] split_limit=limit.split(" - ");
        PreparedStatement myStm=null;
        List<Torrent> torrents=new ArrayList<Torrent>();
        try {
            myStm=ConnectDB.getConnection().prepareStatement(SQL);
            myStm.setInt(1, Integer.parseInt(split_limit[0])-1);
            myStm.setInt(2, Integer.parseInt(split_limit[1])-Integer.parseInt(split_limit[0])+1);
        } catch (SQLException e) {
            return torrents;
        }

        ResultSet myResultSet= null;
        try {
            myResultSet = myStm.executeQuery();
            while (myResultSet.next()) {
                torrents.add(
                        new Torrent(myResultSet.getString("type"), myResultSet.getString("name"),myResultSet.getString("genre"), myResultSet.getString("tags"),
                                myResultSet.getString("link1"), myResultSet.getString("link2"), myResultSet.getInt("no_comments"),
                                myResultSet.getString("added"), myResultSet.getString("size"), myResultSet.getInt("snatched"), myResultSet.getInt("seeds"),
                                myResultSet.getInt("leechs"))
                );
            }
            return torrents;
        } catch (SQLException e) {
            return torrents;
        }
    }

    public static int getTorrentsNo(String type, String search, String in, String status, List<String> categories) {
        String SQL="select count(*) AS NO from torrents where ";
        if (search.isEmpty()) {
                SQL+="1=1 ";
        } else {
            SQL+=in+" like '%"+search+"%' ";
        }
        switch (status) {
            case "All":
                break;
            case "Active":
                SQL+="AND seeds>0 ";
                break;
            case "Dead":
                SQL+="AND seeds=0";
                break;
            default:
                break;
        }
        for (int i=0;i<categories.size();i++) {
            SQL+=" AND type='"+categories.get(i)+"'";
        }
        switch (type) {
            case "Adult":
                SQL+=" AND type in ('Adult')";
                break;
            case "Music":
                SQL+=" AND type in ('Music')";
                break;
            case "Docs":
                SQL+=" AND type in ('Docs')";
                break;
            case "Browse":
                SQL+=" AND type not in ('Adult','Music','Docs')";
                break;
            default:
                break;
        }
        Statement myStm = null;
        try {
            myStm = ConnectDB.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet myResultSet = myStm.executeQuery(SQL);
            if (myResultSet.next()) {
                return myResultSet.getInt("NO");
            }
        } catch (SQLException e) {
            return 0;
        }
        return 0;
    }

    public static int getTorrentID(String name) throws SQLException {
        Statement myStatement=ConnectDB.getConnection().createStatement();
        ResultSet myResultSet=myStatement.executeQuery("select * from torrents where name='"+name+"';");
        if (myResultSet.next()) {
            return myResultSet.getInt("idtorrents");
        } else {
            return -1;
        }
    }

    public static boolean thanksTorrent(String name) throws SQLException {
        Statement myStatement=ConnectDB.getConnection().createStatement();
        ResultSet myResultSet=myStatement.executeQuery("select * from torrents_like where iduser="+UsersDB.getUserID(Shared.user)+" and idtorrent="+getTorrentID(name));
        if (myResultSet.next()) {
            return true;
        } else {
            return false;
        }
    }

    public static void putThanks(String name) throws SQLException {
        int thanks=0;
        //check if thanks column is empty and make first record
        String SQL="select count(*) as no from torrents_like where idtorrent=?";
        PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, getTorrentID(name));
        ResultSet myResultSet=myStatement.executeQuery();
        if (myResultSet.next()) {
            thanks=myResultSet.getInt("no");
        }

        //insert like into torrents_like table
        SQL="insert into torrents_like (iduser,idtorrent) values (?,?)";
        myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, UsersDB.getUserID(Shared.user));
        myStatement.setInt(2, getTorrentID(name));
        myStatement.executeUpdate();

        //insert user what put thanks
        if (thanks==0) {
            SQL="update torrents set thanks=? where idtorrents=?";
            myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, Shared.user);
        } else {
            SQL="update torrents set thanks=CONCAT(thanks,?) where idtorrents=?";
            myStatement=ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, ", "+Shared.user);
        }
        myStatement.setInt(2, getTorrentID(name));
        myStatement.executeUpdate();

        //update user thanks no
        SQL="update users set number_thanks=? where id=?";
        myResultSet=myStatement.executeQuery("select * from users where id="+UsersDB.getUserID(Shared.user));
        int no_thx=0;
        if (myResultSet.next()) {
            no_thx=myResultSet.getInt("number_thanks")+1;
        }
        myStatement=ConnectDB.getConnection().prepareStatement(SQL);
        myStatement.setInt(1, no_thx);
        myStatement.setInt(2, UsersDB.getUserID(Shared.user));
        myStatement.executeUpdate();
    }

    public static List<Torrent> getAllRequests(String action, String condition, String limit) {
        String SQL="SELECT * FROM requests where ";
        switch (action) {
            case "search":
                SQL+="name like '%"+condition+"%'";
                break;
            case "view":
                SQL+="type='"+condition+"'";
                break;
            case "filled":
                SQL+="filled='yes'";
                break;
            case "myrequests":
                SQL+="added_by='"+Shared.user+"'";
                break;
            default:
                SQL+="1=1";
                break;
        }
        List<Torrent> list=new ArrayList<>();
        SQL+=" order by idrequests DESC LIMIT ?,?";
        String[] split_limit=limit.split(" - ");
        PreparedStatement myStm=null;
        try {
            myStm=ConnectDB.getConnection().prepareStatement(SQL);
            myStm.setInt(1, Integer.parseInt(split_limit[0])-1);
            myStm.setInt(2, Integer.parseInt(split_limit[1])-Integer.parseInt(split_limit[0])+1);
            ResultSet myResultSet = myStm.executeQuery();
            while (myResultSet.next()) {
                list.add(new Torrent(myResultSet.getString("type"), myResultSet.getString("name"),
                        myResultSet.getInt("comm"), myResultSet.getString("added"), myResultSet.getString("added_by"),
                        myResultSet.getString("filled"), myResultSet.getInt("votes")));
            }
            return list;
        } catch (SQLException e) {
            return list;
        }

    }

    public static int getRequestsNo(String action, String condition) {
        String SQL="SELECT count(*) as NO FROM requests where ";
        switch (action) {
            case "search":
                SQL+="name like '%"+condition+"%'";
                break;
            case "view":
                SQL+="type='"+condition+"'";
                break;
            case "filled":
                SQL+="filled='yes'";
                break;
            case "myrequests":
                SQL+="added_by='"+Shared.user+"'";
                break;
            default:
                SQL+="1=1";
                break;
        }
        try {
            Statement myStm=ConnectDB.getConnection().createStatement();
            ResultSet myResultSet = myStm.executeQuery(SQL);
            if (myResultSet.next()) {
                return myResultSet.getInt("NO");
            }
            return 0;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static void addRequestVote(String name) {
        String SQL="UPDATE requests set votes=? where name=?";
        try {
            PreparedStatement myStatement = ConnectDB.getConnection().prepareStatement("");
            ResultSet myResultSet=myStatement.executeQuery("select * from requests where name='"+name+"'");
            int votes=0;
            if (myResultSet.next()) {
                votes=myResultSet.getInt("votes")+1;
            }
            myStatement = ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setInt(1, votes);
            myStatement.setString(2, name);
            myStatement.executeUpdate();
        } catch (SQLException e) {
            //TODO
        }
    }

    public static boolean addRequest(Torrent torrent) {
        String SQL="INSERT INTO requests (type, name, genre, description, added, added_by)" +
                " VALUES (?,?,?,?,?,?);";
        try {
            PreparedStatement myStatement = ConnectDB.getConnection().prepareStatement(SQL);
            myStatement.setString(1, torrent.getType());
            myStatement.setString(2, torrent.getName());
            myStatement.setString(3, torrent.getGenre().isEmpty()?"N/A":torrent.getGenre());
            myStatement.setString(4, torrent.getDescription());
            myStatement.setString(5, torrent.getAdded());
            myStatement.setString(6, torrent.getAddedBy());
            myStatement.executeUpdate();
            myStatement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static Torrent getRequest(String name) {
        String SQL="SELECT * FROM requests WHERE name='"+name+"'";
        try {
            Statement myStatement = ConnectDB.getConnection().createStatement();
            ResultSet myResultSet = myStatement.executeQuery(SQL);
            if (myResultSet.next()) {
                return new Torrent(myResultSet.getString("type"), name, myResultSet.getString("genre"),
                        myResultSet.getString("description"), myResultSet.getString("added"), myResultSet.getString("added_by"));
            }
            return new Torrent();
        } catch (SQLException e) {
            return new Torrent();
        }
    }
}
