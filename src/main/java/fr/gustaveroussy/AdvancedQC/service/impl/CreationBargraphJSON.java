package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.gustaveroussy.AdvancedQC.service.ICreationBargraphJSON;

public class CreationBargraphJSON implements ICreationBargraphJSON {
	
	@SuppressWarnings("unchecked")
	public JSONObject createBrGJSON (JSONObject percentList, String path) {
		String filename = "avdancedQC_BGR_mqc.json ";
		path = "/users/hadidjasaid/Documents/";
		String relative = new File(path).toURI().relativize(new File(filename).toURI()).getPath();
	
	//construction du bloc  header
	JSONObject header1 = new JSONObject ();
	JSONObject pconfigComplet= new JSONObject();//=2nde partie du header, permet d'avoir la bonne mise en forme
	JSONObject pconfig1 = new JSONObject ();//info brute du pconfig
	
	header1.put("id", "bargraph_test");
	header1.put("section_name", "Bargraph");
	header1.put("plot_type", "bargraph");
	LOG.debug("header1{}", header1);
	
	pconfig1.put("id", "custom data json bargraph");
	pconfig1.put("title", " percent val nul");
	pconfig1.put("ylab", "Number");
	LOG.debug("pconfig{}", pconfig1);
	
	pconfigComplet.put("pconfig", pconfig1);
	LOG.debug("pconfig{}", pconfigComplet);
	
	//construction du bloc data
	JSONObject data = new JSONObject();
	JSONObject percentData = new JSONObject(); 
	percentData.putAll(percentList);
	
	data.put("data", percentData);
	LOG.debug("data bargraph{}", data);
	
//ensemble des données constituant le fichier json cad header+data
	JSONObject fichierJSONfinal = new JSONObject();
	fichierJSONfinal.putAll(header1);
	fichierJSONfinal.putAll(pconfigComplet);
	fichierJSONfinal.putAll(data);
	
	LOG.info("fichierJSONfinale {}",fichierJSONfinal);  
  
   	 
    //Write JSON file
    try (FileWriter file = new FileWriter(relative)) {

        file.write(fichierJSONfinal.toJSONString());
        file.flush();

    } catch (IOException e) {
        e.printStackTrace();
    }
	
	return fichierJSONfinal;

}
	private static Logger LOG = LoggerFactory
		      .getLogger(CreationBargraphJSON.class);
}