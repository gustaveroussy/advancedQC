package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureFiles;

@Service
public class CreationBPjson2 implements ICreationJSON {
	@Autowired
	IEcritureFiles ecriturePlotly;

	@SuppressWarnings("unused")
	public JsonElement createJSON(List<? extends SamplewHeader> listwHeader) {
		JsonArray bpJsonDirect = new JsonArray();
		
		
	for( SamplewHeader splwh : listwHeader) {
		
		JsonObject property = new JsonObject();
		
		   double q1= splwh.getSampleGeneVal().get("25%");
		   double upperfence= splwh.getSampleGeneVal().get("max");
		   double lowerfence= splwh.getSampleGeneVal().get("min");
		   double q3= splwh.getSampleGeneVal().get("75%");
		   double median = splwh.getSampleGeneVal().get("50%");
		   JsonArray Q1 = new JsonArray();
		   JsonArray Q3 = new JsonArray();
		   JsonArray upper = new JsonArray();
		   JsonArray lower = new JsonArray();
		   JsonArray med = new JsonArray();
		   
		   Q1.add(q1);
		   Q3.add(q3);
		   upper.add(upperfence);
		   lower.add(lowerfence);
		   med.add(median);

		   property.addProperty("type","box");
		   property.addProperty("name", splwh.getSampleID());
		   property.addProperty("boxpoints",false);
		   property.add("lowerfence", lower);
		   property.add("upperfence", upper); 
		   property.add("q1", Q1);
		   property.add("q3", Q3);
		   property.add("median", med);
			
		
			bpJsonDirect.add(property);	
		
	}
		LOG.debug("bpjson{}", bpJsonDirect);
		
		return bpJsonDirect;		
	}
	@Override
	public void export(String filePath, List<? extends SamplewHeader> listwHeader) throws IOException {
		JsonElement fileplotly = this.createJSON(listwHeader);
		ecriturePlotly.ecriturePlotly(fileplotly, filePath + File.separator+ this.getClass().getSimpleName().concat("_mqc.html"));
		LOG.debug("fileplotly{}", fileplotly);

	}
	private static Logger LOG = LoggerFactory.getLogger(CreationBPjson2.class);

}
