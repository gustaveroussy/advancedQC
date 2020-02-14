package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;


public class CalculPercentil {
	
	public List<List <SampleValue>> percentileCalc (List<SamplewHeader> listwHeader) {
		List<List <SampleValue>> percentilelist = new ArrayList<>();
			List<SampleValue> listD1 = percentileValue(listwHeader, 10.0);
			List<SampleValue> listD9 = percentileValue(listwHeader, 90.0);
			List<SampleValue> listQ1 = percentileValue(listwHeader, 25.0);
			List<SampleValue> listQ2 = percentileValue(listwHeader, 50.0);
			List<SampleValue> listQ3 = percentileValue(listwHeader, 75.0);
			percentilelist.add(listD1);
			percentilelist.add(listD9);
			
			LOG.info("percentilelist {}", percentilelist);
			
			return percentilelist;
			
		
	}
	
	private  List<SampleValue> percentileValue (List<SamplewHeader> listwHeader, Double percentile) {
		List<SampleValue> listPercentileCal = new ArrayList <>();
		for(int i= 0; i< listwHeader.size(); i ++ ) {
			Collection<Double> valsampleinter1 =listwHeader.get(i).getSampleGeneVal().values();
			Double [] valsampleinter2= valsampleinter1.toArray(new Double [valsampleinter1.size()]);
			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
			double percentilecal =	StatUtils.percentile(valsamplefinal,percentile);
				LOG.debug("decilemin{}",percentilecal);
		    SampleValue samplevaluespercentile = new SampleValue (listwHeader.get(i).getSampleID(),percentilecal);
		    listPercentileCal.add(samplevaluespercentile);
		}
		LOG.debug("resultat percentile{} ",listPercentileCal);
		return listPercentileCal;
	}
//	for(int i= 0; i< listwHeader.size(); i ++ ) {
//		Collection<Double> valsampleinter1 =listwHeader.get(i).getSampleGeneVal().values();
//		Double [] valsampleinter2= valsampleinter1.toArray(new Double [valsampleinter1.size()]);
//		double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
//		double decilemin =	StatUtils.percentile(valsamplefinal,10);
//			LOG.debug("decilemin{}",decilemin);
//	    SampleValue decileminvalue = new SampleValue (listwHeader.get(i).getSampleID(),decilemin);
//	    listDecileMin.add(decileminvalue);
//		}
//		LOG.info("liste de decilemin{} ",listDecileMin);
//		return listDecileMin;
//		}
	
	private static Logger LOG = LoggerFactory.getLogger(CalculPercentil.class);

}
