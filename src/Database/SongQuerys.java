package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class SongQuerys {
	
	private static Connection conn = DB.getInstance().getConnection();

	private static Statement stmt; 
	public static ResultSet rs;
	
	
	public static ResultSet SelectSongs()
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM songs";
			rs = stmt.executeQuery(query);
			
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
	public static ResultSet SelectSongsByTitle(String title)
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM songs WHERE title = '" + title + "';";
			rs = stmt.executeQuery(query);
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
	
	public static ResultSet SelectSongsByGenre(String gen)
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM songs WHERE genre = '" + gen + "';";
			rs = stmt.executeQuery(query);
			
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
	public static ResultSet SelectSongsByArtist(String artist)
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM songs WHERE artist ='" + artist +"';" ;
			rs = stmt.executeQuery(query);
			
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
	
	public static ResultSet SelectSongsByID(int id)
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM songs WHERE songID = '" + id + "';";
			rs = stmt.executeQuery(query);
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
}
