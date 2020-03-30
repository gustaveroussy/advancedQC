package fr.gustaveroussy.AdvancedQC.service.impl;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;

public class CreationJSON implements ICreationJSON{
	
		@SuppressWarnings("unchecked")
		public JSONObject createJSON (JSONObject D1, JSONObject D9, JSONObject Q1,JSONObject Med,JSONObject Q3 ) {
			String path = "avdancedQC_datamod4_mqc.json";
			String base = "/users/hadidjasaid/Documents/GitHub/advancedQC/MultiQC_file/ ";
			String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();
			
			
			
		//construction du bloc  header
			JSONObject header1 = new JSONObject ();
			JSONObject header2= new JSONObject();
			JSONObject pconfig = new JSONObject ();
			JSONObject myHeader = new JSONObject ();
			
			
			header1.put("id", "beeswarm_test");
			header1.put("section_name", "Beeswarm");
			header1.put("plot_type", "table");
			
			LOG.info("header1{}", header1);
			pconfig.put("id", "custom data json table");
			pconfig.put("title", " beeswarms quantiles");
			pconfig.put("ytype", "linear");
			LOG.info("pconfig{}", pconfig);
			
			header2.put("pconfig", pconfig);
			LOG.info("header2{}", header2);
			
			myHeader.put(header1, header2);
			
		//construction du bloc data
			JSONObject data = new JSONObject();
			JSONObject quantiles = new JSONObject(); 
			quantiles.putAll(D1);
			quantiles.putAll(D9);
			quantiles.putAll(Q1);
			quantiles.putAll(Med);
			quantiles.putAll(Q3);
			
			data.put("data", quantiles);
			
		//ensemble des données constituant le fichier json	
			JSONObject fichierJSONfinal = new JSONObject();
			fichierJSONfinal.putAll(header1);
			fichierJSONfinal.putAll(header2);
			fichierJSONfinal.putAll(data);
			
			LOG.info("fichierJSONfinale {}",fichierJSONfinal);  
	      
	       	 
	        //Write JSON file
	        try (FileWriter file = new FileWriter(relative)) {
	 
	            file.write(fichierJSONfinal.toJSONString());
	            file.flush();
	 
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			return data;
			
	 
	 }
		private static Logger LOG = LoggerFactory
			      .getLogger(CreationJSON.class);
}
