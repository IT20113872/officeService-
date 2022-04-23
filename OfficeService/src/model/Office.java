package model;
import java.sql.*;

import com.google.gson.JsonObject;

import util.DB_Connector;



public class Office {
	
	public static Connection con = null;
	
	public String insertOffice(String OfficeID, String OfficeName, String OfficeType, String OfficeAddress, String OfficePhoneNumber, String BranchManager)
	{
	String output = "";
	try
	{
	con =DB_Connector.connect();
	
	if (con == null)
	{return "Error while connecting to the database for inserting."; }


	//create a prepared statement
			 String query = " insert into newofficedb(`OfficeID`,`OfficeName`,`OfficeType`,`OfficeAddress`,`OfficePhoneNumber`,`BranchManager`)" + " values (?, ?, ?, ?, ?,? )";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 //preparedStmt.setInt(1, 0);
			 preparedStmt.setString(1, OfficeID);
			 preparedStmt.setString(2, OfficeName);
			 preparedStmt.setString(3, OfficeType);
			 preparedStmt.setString(4, OfficeAddress);
			 preparedStmt.setString(5, OfficePhoneNumber);
			 preparedStmt.setString(6, BranchManager);
			 //preparedStmt.setDouble(4, Double.parseDouble(price));
			// preparedStmt.setString(5, desc);
			 
			//execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Inserted successfully";
			 }
			catch (Exception e)
			 {
				
				
				System.out.print("insert wenne na mcn");
			 output = "Error while inserting";
			 System.err.println(e.getMessage());
			 }
			return output;
			}



	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public String readOffice()
	
	{
		 String output = "";
		 try
		 {
			 con =DB_Connector.connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Office ID</th><th>Office Name</th>" +
		 "<th>Office Type</th>" +  "<th>Office Address</th>" +
		 "<th>Phone Number</th>" + "<th>Branch Manager</th>" + 
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from newofficedb";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 //String OfficeID = Integer.toString(rs.getInt("OfficeID"));
		 String OfficeID = rs.getString("OfficeID");
		 String OfficeName = rs.getString("OfficeName");
		 String OfficeType = rs.getString("OfficeType");
		 //String itemPrice = Double.toString(rs.getDouble("itemPrice"));
		 String OfficeAddress = rs.getString("OfficeAddress");
		 String OfficePhoneNumber  = rs.getString("OfficePhoneNumber");
		 String BranchManager  = rs.getString("BranchManager");
		 
		 // Add into the html table
		 output += "<tr><td>" + OfficeID + "</td>";
		 output += "<td>" + OfficeName + "</td>";
		 output += "<td>" + OfficeType + "</td>";
		 output += "<td>" + OfficeAddress + "</td>";
		 output += "<td>" + OfficePhoneNumber + "</td>";
		 output += "<td>" + BranchManager + "</td>";
		 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" +
		 "<td><form method='post' action='/OfficeService/Service/Items/delete'>" + 
				 "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" +
		 "<input name='itemID' type='hidden' value='" + OfficeID + "'>" + "</form></td></tr>"; }
		 con.close();

		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }


	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	public String updateOffice(String OfficeID, String OfficeName, String OfficeType, String OfficeAddress, String OfficePhoneNumber, String BranchManager)

	{
		 String output = "";
		 try
		 {
			 con =DB_Connector.connect();
		 if (con == null)
		 {return "Error while connecting to the database for updating."; }
		 // create a prepared statement
		 String query = "UPDATE newofficedb SET OfficeID=?,OfficeName=?,OfficeType=?,OfficeAddress=?,OfficePhoneNumber=?,BranchManager=? WHERE OfficeID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		
		 // binding values
		 preparedStmt.setString(1, OfficeID);
		 preparedStmt.setString(2, OfficeName);		 
		 preparedStmt.setString(3, OfficeType);
		 preparedStmt.setString(4, OfficeAddress);
		 preparedStmt.setString(5, OfficePhoneNumber);
		 preparedStmt.setString(6, OfficeID);
		 preparedStmt.setString(7, BranchManager);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 
			 System.out.print("Update karanna bariuna");
		 output = "Error while updating the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public String deleteOffice(String OfficeID)
		 {
		 String output = "";
		 try
		 {
			 con =DB_Connector.connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 // create a prepared statement
		 String query = "delete from newofficedb where OfficeID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 //preparedStmt.setInt(1, Integer.parseInt(OfficeID));
		 preparedStmt.setString(1, OfficeID);
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }



//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		public String SelectedOffice(String officeid)
		 {
				String output = "";
				 try
				 {
					 con =DB_Connector.connect();
						 
						 if (con == null)
						 {return "Error while connecting to the database for reading."; }
						 
						 
						 // Prepare the html table to be displayed
						 output = "<table border='1'><tr><th>NIC</th><th>Name</th>" +
						 "<th>DOB</th>" +
						 "<th>Address</th>" +
						 "<th>Phone </th>" +
						 "<th>Salary</th>" +
						 "<th>Type</th>" +
						 "<th>Branch</th>"+"Branch Manager";
					
						 String query = "select * from newofficedb where OfficeID='"+officeid+"'";
						 Statement stmt = con.createStatement();
						 ResultSet rs = stmt.executeQuery(query);
						 
						 
						 // iterate through the rows in the result set
						 while (rs.next())
						 {
							 String OfficeID = rs.getString("OfficeID");
							 String OfficeName = rs.getString("OfficeName");
							 String OfficeType = rs.getString("OfficeType");
							 //String itemPrice = Double.toString(rs.getDouble("itemPrice"));
							 String OfficeAddress = rs.getString("OfficeAddress");
							 String OfficePhoneNumber  = rs.getString("OfficePhoneNumber");	 
							 String BranchManager  = rs.getString("BranchManager");
							 
							 // Add into the html table
							 output += "<tr><td>" + OfficeID + "</td>";
							 output += "<td>" + OfficeName + "</td>";
							 output += "<td>" + OfficeType + "</td>";
							 output += "<td>" + OfficeAddress + "</td>";
							 output += "<td>" + OfficePhoneNumber + "</td>";
							 output += "<td>" + BranchManager + "</td>";
							 
							 // buttons
							 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" +
							 "<td><form method='post' action='/OfficeService/Service/Items/delete'>" + 
									 "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" +
							 "<input name='itemID' type='hidden' value='" + OfficeID + "'>" + "</form></td></tr>"; }
							 con.close();

							 // Complete the html table
							 output += "</table>";
				 }
				 catch (Exception e)
				 {
					 output = "Error while reading";
					 System.err.println(e.getMessage());
				 }
				 	
				 
				 return output;
		 }



		/**Reading Source by the Employee Id**/
		public JsonObject readSourceEmp(String id)
		{
			JsonObject output = null;
			
			try
			{
				con = DB_Connector.connect();
				if (con == null) {
					output=new JsonObject();
					output.addProperty("MESSAGE", "Database connection failed for reading data.");
					//return "Database connection failed for reading data.";
				}
				//
				String query = "select * from powersource where Head_Engineer='"+id+"'";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					JsonObject dbObject = new JsonObject();
					dbObject.addProperty("name", rs.getString("Name"));
					dbObject.addProperty("address", rs.getString("Address"));
					output=dbObject;
					
				}
				con.close();
				
			}
			catch (Exception e)
			{
				output=new JsonObject();
				output.addProperty("MESSAGE","Error while reading the power source details.");
				//output = "Error while reading the power source details.";
				System.err.println(e.getMessage());
			}
			return output;
		}









}
