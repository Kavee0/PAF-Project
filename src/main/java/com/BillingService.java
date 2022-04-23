package com;

import model.Billing; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Billings")

public class BillingService
	{
		 Billing billingObj = new Billing();
		 
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readBillings()
		 {
		 return "Hello";
		 } 
}
