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
	public JSONObject createBrGJSON (JSONObject percentList) {
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
	LOG.debug("pconfig{}", pconfig);
	
	header2.put("pconfig", pconfig);//header 2 = 2nde partie du header avec pconfig
	LOG.debug("header2{}", header2);
	
	myHeader.putAll(header1);
	myHeader.putAll(header2);
	LOG.debug("header bargraph{}", myHeader);
	//construction du bloc data
	JSONObject data = new JSONObject();
	JSONObject percentData = new JSONObject(); 
	percentData.putAll(percentList);
	
	data.put("data", percentData);
	LOG.debug("data bargraph{}", data);
	
//ensemble des données constituant le fichier json cad header+data
	JSONObject fichierJSONfinal = new JSONObject();
	fichierJSONfinal.putAll(header1);
	fichierJSONfinal.putAll(header2);
	fichierJSONfinal.putAll(data);
	
	LOG.debug("fichierJSONfinale {}",fichierJSONfinal);  
  
   	 
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
		      .getLogger(CreationBargraphJSON.class);
}