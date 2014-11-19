package Database;

import java.lang.ClassNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DB {
	private static DB instance = null;
	private static Connection conn = null;
	
	
	public static DB getInstance(){
		if(instance==null)
			instance = new DB();
		
		return instance;
	}
	
	
	public static boolean openConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/antjuke", "root", "P4ssword");
		}
		catch(SQLException error){ error.printStackTrace(); }
		catch(ClassNotFoundException error){ error.printStackTrace(); }
			
			return true;
		}
	
	public static Connection getConnection(){
		
		if(conn == null){
			if(openConnection()){
				System.out.println("connection opened");
				return conn;
			}else{
				return null;
			}
		}
		return conn;
	}
	
	
	public static void close()
	{
		try{
			conn.close();
		}catch(SQLException error) { error.printStackTrace(); }
		
		conn = null;
	}

}
