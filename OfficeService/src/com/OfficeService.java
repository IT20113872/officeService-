package com;
import model.Office;
import com.InterServiceCommunication;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;



//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;



@Path("/office")
public class OfficeService {
	
	Office OfficeObj = new Office();

	//Read Office Data
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
		return OfficeObj.readOffice();
	//return "Hello aravinda ";
	}

	
	
	//Insert Office Data
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertOffice(
	 @FormParam("OfficeID") String OfficeID,
	 @FormParam("OfficeName") String OfficeName,
	 @FormParam("OfficeType") String OfficeType,
	 @FormParam("OfficeAddress") String OfficeAddress,
	 @FormParam("OfficePhoneNumber") String OfficePhoneNumber,
	 @FormParam("BranchManager") String BranchManager)
	
	{
	 String output = OfficeObj.insertOffice(OfficeID, OfficeName, OfficeType, OfficeAddress, OfficePhoneNumber, BranchManager);
	 return output;
	}

	
	//Update Office Data
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateOffice(String OfficeData)
	{
	//Convert the input string to a JSON object
	 JsonObject OfficeObject = new JsonParser().parse(OfficeData).getAsJsonObject();
	//Read the values from the JSON object
	 
	 String OfficeID = OfficeObject.get("OfficeID").getAsString();
	 String OfficeName = OfficeObject.get("OfficeName").getAsString();
	 String OfficeType = OfficeObject.get("OfficeType").getAsString();
	 String OfficeAddress = OfficeObject.get("OfficeAddress").getAsString();
	 String OfficePhoneNumber = OfficeObject.get("OfficePhoneNumber").getAsString();
	 String BranchManager = OfficeObject.get("BranchManager").getAsString();
	 String output = OfficeObj.updateOffice(OfficeID, OfficeName, OfficeType, OfficeAddress, OfficePhoneNumber, BranchManager);
	return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOffice(String OfficeData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(OfficeData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String OfficeID = doc.select("OfficeID").text();
	 String output = OfficeObj.deleteOffice(OfficeID);
	return output;
	}

	
	//Read One Office data
	@GET
	@Path("/{officeid}")
	@Produces(MediaType.TEXT_HTML)
	public String readSelectOffice(@PathParam("officeid") String id)
	//public String readSelectOffice()
	 {
	 return OfficeObj.SelectedOffice(id);
	}

		
	
	
	/*Inter-service communication between the employee*/
	@GET
	@Path("/{Manager_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readEmpSource(@PathParam("Manager_id") String id) {
		
		JsonObject result = null;
		
		result=OfficeObj.readSourceEmp(id);
		return result.toString();
		
	}
	
	//Interservice communication with employee
	@GET
	@Path("/emp/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String readSource(@PathParam("id") String id)
	 {
		 
		 return (new InterServiceCommunication().EmployeeDetails("/" + id)).toString();
		 
	}
	
	
	}


