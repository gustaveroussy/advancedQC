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
	public List<SampleValue> calculDecileMin(SamplewHeader sample) {
		// TODO Auto-generated method stub
		List<SampleValue> listD1 = percentileValue(sample, 10.0);
		LOG.debug("résultat D1: {}", listD1);
		return listD1;
	}

	public List<SampleValue> calculDecileMax (SamplewHeader sample) {
		List<SampleValue> listD9 = percentileValue(sample, 90.0);
		LOG.debug("résultat D9: {}", listD9);
		return listD9;
	}
	
//MEDIANE
	
	public List<SampleValue> calculMediane (SamplewHeader sample) {
		List<SampleValue> listmed = percentileValue(sample, 50.0);
		LOG.debug("résultat median: {}", listmed);
		return listmed;
	}

//QUARTILES
	public List<SampleValue> calculQ1 (SamplewHeader sample) {
		List<SampleValue> listQ1 = percentileValue(sample, 25.0);
		LOG.debug("résultat Q1: {}", listQ1);
		return listQ1;
	}

	public List<SampleValue> calculQ3(SamplewHeader sample) {
		List<SampleValue> listQ3 = percentileValue(sample, 75.0);
		LOG.debug("résultat Q3: {}", listQ3);
		return listQ3;
	}
	
//	private List<SampleValue> percentileValue(List<SamplewHeader> listwHeader, Double percentile) {
//		List<SampleValue> listPercentileCal = new ArrayList<>();
//		for (int i = 0; i < listwHeader.size(); i++) {
//			Collection<Double> valsampleinter1 = listwHeader.get(i).getSampleGeneVal().values();
//			Double[] valsampleinter2 = valsampleinter1.toArray(new Double[valsampleinter1.size()]);
//			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
//			double percentilecal = StatUtils.percentile(valsamplefinal, percentile);
//			LOG.debug("decilemin{}", percentilecal);
//			SampleValue samplevaluespercentile = new SampleValue(listwHeader.get(i).getSampleID(), percentilecal);
//			listPercentileCal.add(samplevaluespercentile);
//		}
//		LOG.debug("resultat percentile{} ", listPercentileCal);
//		return listPercentileCal;
//	}
	
	private static Logger LOG = LoggerFactory.getLogger(DistributionDesNivExpression.class);
	
	
	//Modification de la methode pour json
	private List<SampleValue> percentileValue(SamplewHeader sample, Double percentile) {//en argument on ne prend plus la listwheader mais samplweader qui contient deja les valeurs recherchées
		List<SampleValue> listPercentileCal = new ArrayList<>();
		for (int i = 0; i< sample.getSampleGeneVal().size(); i++){//taille de la map
			Collection<Double> valsample1= sample.getSampleGeneVal().values();
			Double[] valsample2 = valsample1.toArray(new Double[valsample1.size()]);
			double[] valsample3 = ArrayUtils.toPrimitive(valsample2);
			double percentilecal2 = StatUtils.percentile(valsample3, percentile);
//          LOG.debug("decilemin{}", percentilecal2);
			SampleValue samplevaluespercentile = new SampleValue(sample.getSampleID(), percentilecal2);
			listPercentileCal.add(samplevaluespercentile);
		}
		LOG.debug("resultat percentile{} ", listPercentileCal);
		return listPercentileCal;
	}
	
	
	
	
	
	
	
	
	

}
