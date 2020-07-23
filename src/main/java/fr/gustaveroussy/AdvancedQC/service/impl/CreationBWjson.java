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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureFiles;

@Service
public class CreationBWjson implements ICreationJSON {
	@Autowired
	IEcritureFiles ecritureMqc;


	@Override
	public JsonObject createJSON(List<? extends SamplewHeader> listwHeader) {
		JsonObject bwJSONfinal = new JsonObject();
		JsonObject decilemin = calculDecileMin(listwHeader);
		JsonObject decilemax = calculDecileMax(listwHeader);
		JsonObject quartileQ1 = calculQ1(listwHeader);
		JsonObject quartileQ3 = calculQ3(listwHeader);
		JsonObject mediane = calculMediane(listwHeader);
		LOG.debug("D1 {}", decilemin + "D9 {}", decilemax + "mediane {}", mediane + "Q1 {}", quartileQ1 + "Q3 {}",
				quartileQ3);
		JsonObject headerBW = new JsonObject();
		JsonObject pconfigCompletBW = new JsonObject();// =2nde partie du header, permet d'avoir la bonne mise en forme
														// de pconfig
		
		// ensemble des données constituant le header fichier json 
		bwJSONfinal.addProperty("id", "beeswarm");
		bwJSONfinal.addProperty("section_name", "Beeswarm");
		bwJSONfinal.addProperty("plot_type", "table");
		LOG.debug("header1{}", headerBW);

		JsonObject pconfigBW1 = new JsonObject();// infos brutes du pconfig
		pconfigBW1.addProperty("id", "custom data json table");
		pconfigBW1.addProperty("title", " beeswarms quantiles");
		pconfigBW1.addProperty("ytype", "linear");
		LOG.debug("pconfig{}", pconfigBW1);

		bwJSONfinal.add("pconfig", pconfigBW1);
		LOG.debug("header2{}", pconfigCompletBW);

		// construction du bloc data BW
		
		JsonObject data = new JsonObject();
		data.add("Decile Min",calculDecileMin(listwHeader));
		data.add("Decile Max",calculDecileMax(listwHeader));
		data.add("Quantile Min",calculQ1(listwHeader));
		data.add("Quantile Max",calculQ3(listwHeader));
		data.add("Mediane",calculMediane(listwHeader));
		
		bwJSONfinal.add("data", data);


		LOG.debug("beeswarm {}", bwJSONfinal);

		return bwJSONfinal;
	}

	// Methode PercentilValue permet de calculer les quantiles
	private JsonObject percentileValue(List<? extends SamplewHeader> listwHeader, Double percentile) {
		JsonObject quantilDetails = new JsonObject();
		

		for (int i = 0; i < listwHeader.size(); i++) {
			Collection<Double> valsampleinter1 = listwHeader.get(i).getSampleGeneVal().values();
			Double[] valsampleinter2 = valsampleinter1.toArray(new Double[valsampleinter1.size()]);
			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
			double percentilecal = StatUtils.percentile(valsamplefinal, percentile);
			LOG.debug("decilemin{}", percentilecal);
			quantilDetails.addProperty(listwHeader.get(i).getSampleID(), percentilecal);
		}
		return quantilDetails;
	}

	// Deciles
	private JsonObject calculDecileMin(List<? extends SamplewHeader> listwHeader) {
		JsonObject listD1 = percentileValue(listwHeader, 10.0);
		LOG.debug("résultat D1: {}", listD1);
		return listD1;
	}

	private JsonObject calculDecileMax(List<? extends SamplewHeader> listwHeader) {
		JsonObject listD9 = percentileValue(listwHeader, 90.0);
		LOG.debug("résultat D9: {}", listD9);
		return listD9;
	}
	
	// Mediane
	private JsonObject calculMediane(List<? extends SamplewHeader> listwHeader) {
		JsonObject listmed = percentileValue(listwHeader, 50.0);
		LOG.debug("résultat median: {}", listmed);
		return listmed;
	}
	
	// Quartiles
	private JsonObject calculQ1(List<? extends SamplewHeader> listwHeader) {
		JsonObject listQ1 = percentileValue(listwHeader, 25.0);
		LOG.debug("résultat Q1: {}", listQ1);
		return listQ1;
	}
	private JsonObject calculQ3(List<? extends SamplewHeader> listwHeader) {
		JsonObject listQ3 = percentileValue(listwHeader, 75.0);

		LOG.debug("résultat Q3: {}", listQ3);
		return listQ3;
	}

	private static Logger LOG = LoggerFactory.getLogger(CreationBWjson.class);


	@Override
	public void export(String filePath, List<SamplewHeader> listwHeader) throws IOException {
			JsonElement filemqc = this.createJSON(listwHeader);
			ecritureMqc.ecritureMqc(filemqc,
					filePath + this.getClass().getSimpleName().concat("_mqc.json"));
			LOG.debug("filemqc{}", filemqc);
			//return filemqc;
			
		}
		
	}
