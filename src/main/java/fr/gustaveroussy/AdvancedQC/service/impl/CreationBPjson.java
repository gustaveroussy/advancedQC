package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.IOException;

import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
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
public class CreationBPjson implements ICreationJSON {
	@Autowired
	IEcritureFiles ecriturePlotly;

	@Override
	public JsonObject createJSON (List<? extends SamplewHeader> listwHeader ) {
		JsonObject bpJSONfinal = new JsonObject();
		JsonArray myConfig = new JsonArray();
		
		for (SamplewHeader samplh : listwHeader) {			 
			Collection<Double> valsampleinter1 = samplh.getSampleGeneVal().values();
			Double[] valsampleinter2 = valsampleinter1.toArray(new Double[valsampleinter1.size()]);
			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
			double decilmin = StatUtils.percentile(valsamplefinal, 10);
			double decilmax = StatUtils.percentile(valsamplefinal, 90);
			double Q1 = StatUtils.percentile(valsamplefinal, 25);
			double Q3 = StatUtils.percentile(valsamplefinal, 75);
			double med = StatUtils.percentile(valsamplefinal, 50);
			
				JsonArray y = new JsonArray();
				JsonObject propriety = new JsonObject();

				y.add(decilmin);
				y.add(decilmax);
				y.add(Q1);
				y.add(Q3);
				y.add(med);
				LOG.debug("y{},", y);
				
				propriety.addProperty("name", samplh.getSampleID());
				propriety.addProperty("type", "box");
				propriety.add("y", y);
				myConfig.add(propriety);
				
				bpJSONfinal.add("myConfig", myConfig);
				LOG.debug("bpjson{},", bpJSONfinal);
			}
			LOG.debug("myconfig {}", myConfig);
			return bpJSONfinal;
		}
		

	@Override
	public void export(String filePath, List<SamplewHeader> listwHeader) throws IOException {
		JsonElement filemqc = this.createJSON(listwHeader);
		ecriturePlotly.ecriturePlotly(filemqc, filePath + this.getClass().getSimpleName().concat("_mqc.json"));
		LOG.info("filemqc{}", filemqc);
		//return filemqc;

	}

	private static Logger LOG = LoggerFactory.getLogger(CreationBPjson.class);

}


		


