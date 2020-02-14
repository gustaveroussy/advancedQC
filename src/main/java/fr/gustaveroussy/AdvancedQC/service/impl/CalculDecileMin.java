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


public class CalculDecileMin {
	public List<SampleValue> decilemin (List<SamplewHeader> listwHeader) {
		List<SampleValue> listDecileMin = new ArrayList <>();
		//double [] valsample ;
		
		
		for(int i= 0; i< listwHeader.size(); i ++ ) {
		Collection<Double> valsampleinter1 =listwHeader.get(i).getSampleGeneVal().values();
		Double [] valsampleinter2= valsampleinter1.toArray(new Double [valsampleinter1.size()]);
		double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
		double decilemin =	StatUtils.percentile(valsamplefinal,10);
			LOG.debug("decilemin{}",decilemin);
	    SampleValue decileminvalue = new SampleValue (listwHeader.get(i).getSampleID(),decilemin);
	    listDecileMin.add(decileminvalue);
	    
		}
		LOG.info("liste de decilemin{} ",listDecileMin);
		return listDecileMin;
		}
	private static Logger LOG = LoggerFactory.getLogger(CalculDecileMin.class);

}
