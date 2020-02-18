package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.IDistributionNivExpression;

public class DistributionDesNivExpression implements IDistributionNivExpression {

//DECILES
	@Override
	public List<SampleValue> calculDecileMin(List<SamplewHeader> listwHeader) {
		// TODO Auto-generated method stub
		List<SampleValue> listD1 = percentileValue(listwHeader, 10.0);
		LOG.debug("résultat D1: {}", listD1);
		return listD1;
	}

	public List<SampleValue> calculDecileMax(List<SamplewHeader> listwHeader) {
		List<SampleValue> listD9 = percentileValue(listwHeader, 90.0);
		LOG.debug("résultat D9: {}", listD9);
		return listD9;
	}
	
//MEDIANE
	@Override
	public List<SampleValue> calculMediane(List<SamplewHeader> listwHeader) {
		List<SampleValue> listmed = percentileValue(listwHeader, 50.0);
		LOG.debug("résultat median: {}", listmed);
		return listmed;
	}

//QUARTILES
	@Override
	public List<SampleValue> calculQ1(List<SamplewHeader> listwHeader) {
		List<SampleValue> listQ1 = percentileValue(listwHeader, 25.0);
		LOG.debug("résultat Q1: {}", listQ1);
		return listQ1;
	}

	public List<SampleValue> calculQ3(List<SamplewHeader> listwHeader) {
		List<SampleValue> listQ3 = percentileValue(listwHeader, 75.0);
		LOG.debug("résultat Q3: {}", listQ3);
		return listQ3;
	}
	
	private List<SampleValue> percentileValue(List<SamplewHeader> listwHeader, Double percentile) {
		List<SampleValue> listPercentileCal = new ArrayList<>();
		for (int i = 0; i < listwHeader.size(); i++) {
			Collection<Double> valsampleinter1 = listwHeader.get(i).getSampleGeneVal().values();
			Double[] valsampleinter2 = valsampleinter1.toArray(new Double[valsampleinter1.size()]);
			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
			double percentilecal = StatUtils.percentile(valsamplefinal, percentile);
			LOG.debug("decilemin{}", percentilecal);
			SampleValue samplevaluespercentile = new SampleValue(listwHeader.get(i).getSampleID(), percentilecal);
			listPercentileCal.add(samplevaluespercentile);
		}
		LOG.debug("resultat percentile{} ", listPercentileCal);
		return listPercentileCal;
	}
	
	private static Logger LOG = LoggerFactory.getLogger(DistributionDesNivExpression.class);

}
