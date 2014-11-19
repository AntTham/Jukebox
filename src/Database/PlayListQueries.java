package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Models.Songs;

public class PlayListQueries {
	private static Connection conn = DB.getInstance().getConnection();

	private static Statement stmt; 
	public static ResultSet rs;
	
	
	public void createNewPL(String name){
		try{
			stmt = conn.createStatement();
			String query ="CREATE TABLE `antjuke`.`" + name + "` ("
					  +"`index` INT NOT NULL AUTO_INCREMENT,"
					  +"`songID` INT(11) NULL,"
					  +"PRIMARY KEY (`index`));";
					
			stmt.execute(query);
		}catch(Exception error) { error.printStackTrace();}
	}
	
	
	public void insertNametoPLList(String name)
	{
		try{
			stmt = conn.createStatement();
			String query ="INSERT INTO playlistnames VALUES ('"+ name +"')";
			stmt.executeUpdate(query);
		}catch(Exception error) { error.printStackTrace();}
	}
	
	
	public static ResultSet SelectPL()
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM playlistnames";
			rs = stmt.executeQuery(query);
			
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
	
	public int countSongs(String plName)
	{
		int number = 0;
	
		try{
			stmt = conn.createStatement();
			String query = "SELECT COUNT(*) FROM " + plName;
			rs = stmt.executeQuery(query);
			rs.next();
			number = rs.getInt(1);
		}catch(Exception error) { error.printStackTrace();}
		
		return number;
	}
	
	
	public static ResultSet getPLSongs(String plName)
	{
		try{
			stmt = conn.createStatement();
			String query ="SELECT * FROM " + plName;
			rs = stmt.executeQuery(query);
			
		}catch(Exception error) { error.printStackTrace();}
		return rs;
	}
	
	
	public void insertSongtoPLList(Songs song, String plName)
	{
		int ID = song.getID();
		
		try{
			stmt = conn.createStatement();
			String query ="INSERT INTO " + plName + "(songID) VALUES (" + ID +")";
			stmt.executeUpdate(query);
		}catch(Exception error) { error.printStackTrace();}
	}
	

}
