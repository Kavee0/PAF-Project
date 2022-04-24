package model;

import java.sql.*;

public class Billing {

	// connect to the DB
	private Connection connect()
	 {
	 Connection con = null;
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");

	 //Provide the correct details: DBServer/DBName, username, password
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
	 }
	 catch (Exception e)
	 {e.printStackTrace();}
	 return con;
	 }
	public String insertBilling(String accountnumber, String name, String units)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 
	 // create a prepared statement
	 String query = " insert into billings
	 (`billingID`,`billingAccountNumber`,`billingName`,`billingUnits`)"
	 + " values (?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, accountnumber);
	 preparedStmt.setString(3, name);
	 preparedStmt.setString(4, units);
	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Added Successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the billing.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readBillings()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Billing AccountNumber</th><th>Billing Name</th>" +
	 "<th>Billing Units</th>" +
	 "<th>Update</th><th>Remove</th></tr>";

	 String query = "select * from billing";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String billingID = Integer.toString(rs.getInt("billingID"));
	 String billingAccountNumber = rs.getString("billingAccountNumber");
	 String billingName = rs.getString("billingName");
	 String billingUnits = rs.getString("billingUnits");
	 
	 // Add into the html table
	 output += "<tr><td>" + billingAccountNumber + "</td>";
	 output += "<td>" + billingName + "</td>";
	 output += "<td>" + billingUnits + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'
	 class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='billings.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'
	 class='btn btn-danger'>"
	 + "<input name='billingID' type='hidden' value='" + billingID
	 + "'>" + "</form></td></tr>";
	 }
	 con.close();
	 
	 // Complete the html table
	 output += "</table>";
	 } 
	 catch (Exception e)
	 {
	 output = "Error while reading the billings.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	//update
		public String updateBilling(String ID, String accountnumber, String name,  String units)
		
		{
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE billings SET billingAccountNumber=?,billingName=?,billingUnits=?
			 WHERE billingID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setString(1, accountnumber);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(4, units);
			 preparedStmt.setInt(5, Integer.parseInt(ID));
			 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 output = "Updated Successfully";
			 }
			 catch (Exception e)
			 {
			 output = "Error while updating the billing.";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
		
		//DELETE
		public String deleteBilling(String billingID)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for deleting."; }
		 
		 // create a prepared statement
		 String query = "delete from billings where billingID=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(billingID));
		 
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while deleting the billing.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
	
	
}
