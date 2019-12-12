package main.java.com.andreas.tracker.iplay.database;
import main.java.com.andreas.tracker.iplay.model.News;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NewsDB {

	public static void addNews(News news) throws SQLException {
		String SQL="INSERT INTO news (title,content,date) values (?,?,?);";
		PreparedStatement myStatement=ConnectDB.getConnection().prepareStatement(SQL);
		myStatement.setString(1, news.getTitle());
		myStatement.setString(2, news.getContent());
		myStatement.setString(3, news.getDate());
		myStatement.executeUpdate();
		myStatement.close();
	}

	public static void editNews(News news, String old_title) throws SQLException {
		String sql="update news set title=?, content=?, date=? where title=?";
		PreparedStatement myStmt=ConnectDB.getConnection().prepareStatement(sql);
		myStmt.setString(1,news.getTitle());
		myStmt.setString(2, news.getContent());
		myStmt.setString(3, news.getDate());
		myStmt.setString(4,old_title);
		myStmt.executeUpdate();
		myStmt.close();
	}

	public static void deleteNews(String title) throws SQLException {
		String sql = "delete from news where title=?";
		PreparedStatement myStmt = ConnectDB.getConnection().prepareStatement(sql);
		myStmt.setString(1, title);
		myStmt.executeUpdate();
		myStmt.close();
	}

	public static News getLastNews() throws SQLException{
		  News news=new News();
		  Statement myStm=ConnectDB.getConnection().createStatement();
		  ResultSet myRs=myStm.executeQuery("select * from news order by id DESC LIMIT 1");
		  while (myRs.next()){
			  news=new News(myRs.getString("title"), myRs.getString("content"), myRs.getString("date"));
		  }
		 return news;
	}

	public static List<String> getTitlesNews() throws SQLException {
		Statement myStm=ConnectDB.getConnection().createStatement();
		ResultSet myRs=myStm.executeQuery("select * from news order by id DESC");
		List<String> title=new ArrayList<String>();
		while (myRs.next())
			title.add(myRs.getString("title"));
		return title;
	}

	public static String getContentNews(String title) throws SQLException {
		String sql="select * from news where title=?";
		PreparedStatement myStmt=ConnectDB.getConnection().prepareStatement(sql);
		myStmt.setString(1,title);
		ResultSet myRs=myStmt.executeQuery();
		while (myRs.next())
			return myRs.getString("content");
		return "";
	}

}