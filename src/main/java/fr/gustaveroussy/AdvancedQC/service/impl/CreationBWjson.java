package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;

public class CreationBWjson implements ICreationJSON {

//CREATION BEESWARM
	@SuppressWarnings("unchecked")
	public JSONObject createJSON(List<SamplewHeader> listwHeader) {
		JSONObject decilemin = calculDecileMin(listwHeader);
		JSONObject decilemax = calculDecileMax(listwHeader);
		JSONObject quartileQ1 = calculQ1(listwHeader);
		JSONObject quartileQ3 = calculQ3(listwHeader);
		JSONObject mediane = calculMediane(listwHeader);
		LOG.debug("D1 {}", decilemin + "D9 {}", decilemax + "mediane {}", mediane + "Q1 {}", quartileQ1 + "Q3 {}",
				quartileQ3);
		JSONObject headerBW = new JSONObject();
		JSONObject pconfigCompletBW = new JSONObject();// =2nde partie du header, permet d'avoir la bonne mise en forme
														// de pconfig
		JSONObject pconfigBW1 = new JSONObject();// infos brutes du pconfig

		headerBW.put("id", "beeswarm");
		headerBW.put("section_name", "Beeswarm");
		headerBW.put("plot_type", "table");
		LOG.debug("header1{}", headerBW);

		pconfigBW1.put("id", "custom data json table");
		pconfigBW1.put("title", " beeswarms quantiles");
		pconfigBW1.put("ytype", "linear");
		LOG.debug("pconfig{}", pconfigBW1);

		pconfigCompletBW.put("pconfig", pconfigBW1);
		LOG.debug("header2{}", pconfigCompletBW);

		// construction du bloc data BW
		JSONObject data = new JSONObject();
		JSONObject quantiles = new JSONObject();
		quantiles.putAll(decilemin);
		quantiles.putAll(decilemax);
		quantiles.putAll(quartileQ1);
		quantiles.putAll(mediane);
		quantiles.putAll(quartileQ3);

		data.put("data", quantiles);

		// ensemble des données constituant le fichier json cad header+data
		JSONObject bwJSONfinal = new JSONObject();
		bwJSONfinal.putAll(headerBW);// putAll ne rajoute pas de quote supp
		bwJSONfinal.putAll(pconfigCompletBW);
		bwJSONfinal.putAll(data);

		LOG.debug("beeswarm {}", bwJSONfinal);

		return bwJSONfinal;
	}

	// Methode PercentilValue permet de calculer les quantiles
	@SuppressWarnings("unchecked")
	private JSONObject percentileValue(List<SamplewHeader> listwHeader, Double percentile) {
		JSONObject quantilDetails = new JSONObject();
		JSONObject quantilGrp = new JSONObject();

		for (int i = 0; i < listwHeader.size(); i++) {
			Collection<Double> valsampleinter1 = listwHeader.get(i).getSampleGeneVal().values();
			Double[] valsampleinter2 = valsampleinter1.toArray(new Double[valsampleinter1.size()]);
			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
			double percentilecal = StatUtils.percentile(valsamplefinal, percentile);
			LOG.debug("decilemin{}", percentilecal);
			quantilDetails.put(listwHeader.get(i).getSampleID(), percentilecal);
			quantilGrp.put(percentile, quantilDetails);
		}
		return quantilGrp;
	}

	// Déciles
	private JSONObject calculDecileMin(List<SamplewHeader> listwHeader) {
		// TODO Auto-generated method stub
		JSONObject listD1 = percentileValue(listwHeader, 10.0);
		LOG.debug("résultat D1: {}", listD1);
		return listD1;
	}

	private JSONObject calculDecileMax(List<SamplewHeader> listwHeader) {
		JSONObject listD9 = percentileValue(listwHeader, 90.0);
		LOG.debug("résultat D9: {}", listD9);
		return listD9;
	}
	
	// Mediane
	private JSONObject calculMediane(List<SamplewHeader> listwHeader) {
		JSONObject listmed = percentileValue(listwHeader, 50.0);
		LOG.debug("résultat median: {}", listmed);
		return listmed;
	}
	
	// Quartiles
	private JSONObject calculQ1(List<SamplewHeader> listwHeader) {
		JSONObject listQ1 = percentileValue(listwHeader, 25.0);
		LOG.debug("résultat Q1: {}", listQ1);
		return listQ1;
	}
	private JSONObject calculQ3(List<SamplewHeader> listwHeader) {
		JSONObject listQ3 = percentileValue(listwHeader, 75.0);

		LOG.debug("résultat Q3: {}", listQ3);
		return listQ3;
	}

	private static Logger LOG = LoggerFactory.getLogger(CreationBWjson.class);
}