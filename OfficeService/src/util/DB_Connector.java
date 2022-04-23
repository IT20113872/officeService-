package util;
import java.sql.Connection;
import java.sql.DriverManager;


public class DB_Connector {
	
	public static Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3307/ElectroGrid", "root", "");
	 System.out.print("Successfully connected");
	 }
	 catch (Exception e)
	 {
		 System.out.print("Errorrrrrrrrrrrr");
		 e.printStackTrace();}
	 return con;
	 }

}
