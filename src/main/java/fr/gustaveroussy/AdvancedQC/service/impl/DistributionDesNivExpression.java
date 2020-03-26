package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
  
import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.IDistributionNivExpression;
import org.json.simple.JSONArray;


public class DistributionDesNivExpression implements IDistributionNivExpression {
	
//DECILES
	@Override
	public JSONObject calculDecileMin(List<SamplewHeader> listwHeader) {
		// TODO Auto-generated method stub
		JSONObject listD1 = percentileValue(listwHeader, 10.0);
		LOG.debug("résultat D1: {}", listD1);
	//	JSONObject crejson = createJSON(listD1);
	//	LOG.info("json{}",crejson);
		return listD1;
	}
	

	public JSONObject calculDecileMax(List<SamplewHeader> listwHeader) {
		JSONObject listD9 = percentileValue(listwHeader, 90.0);
		LOG.debug("résultat D9: {}", listD9);
		return listD9;
	}
	
//MEDIANE
	@Override
	public JSONObject calculMediane(List<SamplewHeader> listwHeader) {
		JSONObject listmed = percentileValue(listwHeader, 50.0);
		LOG.debug("résultat median: {}", listmed);
		return listmed;
	}

//QUARTILES
	@Override
	public JSONObject calculQ1(List<SamplewHeader> listwHeader) {
		JSONObject listQ1 = percentileValue(listwHeader, 25.0);
		LOG.debug("résultat Q1: {}", listQ1);
		return listQ1;
	}

	public JSONObject calculQ3(List<SamplewHeader> listwHeader) {
		JSONObject listQ3 = percentileValue(listwHeader, 75.0);
		
		LOG.debug("résultat Q3: {}", listQ3);	
		return listQ3;
	}
		
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
	
	private static Logger LOG = LoggerFactory
		      .getLogger(DistributionDesNivExpression.class);
}
