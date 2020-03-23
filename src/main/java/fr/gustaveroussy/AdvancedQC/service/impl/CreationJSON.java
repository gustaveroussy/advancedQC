package fr.gustaveroussy.AdvancedQC.service.impl;


import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;

public class CreationJSON implements ICreationJSON{
	
		@SuppressWarnings("unchecked")
		public JSONArray createJSON (JSONObject Q1, JSONObject Q2, JSONObject Q3,JSONObject Q4,JSONObject Q5 ) {
			JSONArray quantilList = new JSONArray();		
	        quantilList.add(Q1);
	        quantilList.add(Q2);
	        quantilList.add(Q3);
	        quantilList.add(Q4);
	        quantilList.add(Q5);
	       	      
	        //Write JSON file
	        try (FileWriter file = new FileWriter("/users/hadidjasaid/data/avdancedQC_test.json")) {
	 
	            file.write(quantilList.toJSONString());
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return quantilList;
	 
	 }
}
