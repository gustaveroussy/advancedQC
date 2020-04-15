package fr.gustaveroussy.AdvancedQC.service.impl;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.gustaveroussy.AdvancedQC.service.ICreationBeeswarmJSON;

public class CreationBeeswarmJSON implements ICreationBeeswarmJSON{
	
		@SuppressWarnings("unchecked")
		public JSONObject createBWJSON (JSONObject D1, JSONObject D9, JSONObject Q1,JSONObject Med,JSONObject Q3) {
			//String fileName = "BW3_mqc.json";
			//String relative = new File(basePath).toURI().relativize(new File(fileName).toURI()).getPath();	
			//LOG.info("relative path {}",relative);
			//LOG.info("basePath{}",basePath);
		//construction du bloc  header
			JSONObject header1 = new JSONObject ();
			JSONObject pconfigComplet= new JSONObject();//=2nde partie du header, permet d'avoir la bonne mise en forme
			JSONObject pconfig1 = new JSONObject ();//info brute du pconfig
			
			header1.put("id", "beeswarm_test");
			header1.put("section_name", "Beeswarm");
			header1.put("plot_type", "table");
			LOG.debug("header1{}", header1);
			
			pconfig1.put("id", "custom data json table");
			pconfig1.put("title", " beeswarms quantiles");
			pconfig1.put("ytype", "linear");
			LOG.debug("pconfig{}", pconfig1);
			
			pconfigComplet.put("pconfig", pconfig1);
			LOG.debug("header2{}", pconfigComplet);		
			
		//construction du bloc data
			JSONObject data = new JSONObject();
			JSONObject quantiles = new JSONObject(); 
			quantiles.putAll(D1);
			quantiles.putAll(D9);
			quantiles.putAll(Q1);
			quantiles.putAll(Med);
			quantiles.putAll(Q3);
			
			data.put("data", quantiles);
			
		//ensemble des données constituant le fichier json cad header+data
			JSONObject fichierJSONfinal = new JSONObject();
			fichierJSONfinal.putAll(header1);//putAll ne rajoute pas de quote supp
			fichierJSONfinal.putAll(pconfigComplet);
			fichierJSONfinal.putAll(data);
			
			LOG.debug("beeswarm {}",fichierJSONfinal);
			
	      
	     //Write JSON file
//	        try (FileWriter file = new FileWriter(basePath)) {
//	            file.write(fichierJSONfinal.toJSONString());
//	            file.close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
			return fichierJSONfinal;
  }
	
		private static Logger LOG = LoggerFactory
			      .getLogger(CreationBeeswarmJSON.class);
}