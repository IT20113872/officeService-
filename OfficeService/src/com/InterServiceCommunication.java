package com;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class InterServiceCommunication {
	
			private static final String PROTOCOL = "http://";
			private static final String HOST = "127.0.0.1";
			private static final String PORT = "8090";
			private static final String EMPLOYEE_URI = PROTOCOL + HOST + ":" + PORT + "/EmployeeService/ElectroGrid/Employee/Head";
		
		public JsonObject EmployeeDetails(String path) {
			
			

				Client client = Client.create();

				WebResource webResource = client.resource(EMPLOYEE_URI+path);

				ClientResponse response = webResource.accept("application/json")
		                   .get(ClientResponse.class);

				if (response.getStatus() != 200) {
				   throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
				}

				String output = response.getEntity(String.class);

				
				
				JsonObject JSONoutput = new JsonParser().parse(output).getAsJsonObject();
				return JSONoutput;

			  
		}
}
