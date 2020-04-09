package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreationBargraphJSON {
	@SuppressWarnings("unchecked")
	public JSONObject createBrGJSON (JSONObject ech1, JSONObject ech2) {
		String path = "avdancedQC_brg_mqc.json";
		String base = "/users/hadidjasaid/Documents/GitHub/advancedQC/MultiQC_file/ ";
		String relative = new File(base).toURI().relativize(new File(path).toURI()).getPath();	
	
	//construction du bloc  header
	JSONObject header1 = new JSONObject ();
	JSONObject header2= new JSONObject();
	JSONObject pconfig = new JSONObject ();
	JSONObject myHeader = new JSONObject ();
	
	
	header1.put("id", "bargraph_test");
	header1.put("section_name", "Bargraph");
	header1.put("plot_type", "bargraph");
	LOG.info("header1{}", header1);
	
	pconfig.put("id", "custom data json bargraph");
	pconfig.put("title", " percent val nul");
	pconfig.put("ylab", "Number");
	LOG.info("pconfig{}", pconfig);
	
	header2.put("pconfig", pconfig);
	LOG.info("header2{}", header2);
	
	myHeader.put(header1, header2);
	
	//construction du bloc data
	JSONObject data = new JSONObject();
	JSONObject percent = new JSONObject(); 
	percent.putAll(ech1);
	percent.putAll(ech2);

	
	data.put("data", percent);
	
//ensemble des données constituant le fichier json cad header+data
	JSONObject fichierJSONfinal = new JSONObject();
	fichierJSONfinal.putAll(header1);//putAll ne rajoute pas de quote supp
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
		      .getLogger(CreationBeeswarmJSON.class);
}
