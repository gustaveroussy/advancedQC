package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureFiles;

@Primary //Field creationjson2 required a single bean, but 2 were found
@Service
public class CreationBPjsonDirect extends CreationBPjson implements ICreationJSON {
	@Autowired
	IEcritureFiles ecriturePlotly;

	public JsonElement createJSON(List<? extends SamplewHeader> listwHeader) {
		JsonArray bpJsonDirect = new JsonArray();
		JsonArray percentildata = new JsonArray();
		JsonObject property = new JsonObject();
		for( SamplewHeader splwh : listwHeader) {
			
			LOG.debug("y{},", percentildata);
			property.add("y", percentildata);
			
			property.addProperty("type","box");
			property.addProperty("boxpoints",false);
		
		bpJsonDirect.add(property);	
		LOG.debug("bpjson{}", bpJsonDirect);
		return bpJsonDirect;
	}
	}
	@Override
	public void export(String filePath, List<? extends SamplewHeader> listwHeader) throws IOException {
		JsonElement fileplotly = this.createJSON(listwHeader);
		ecriturePlotly.ecriturePlotly(fileplotly, filePath + File.separator+ this.getClass().getSimpleName().concat("_mqc.html"));
		LOG.debug("fileplotly{}", fileplotly);

	}
	private static Logger LOG = LoggerFactory.getLogger(CreationBPjsonDirect.class);

}
