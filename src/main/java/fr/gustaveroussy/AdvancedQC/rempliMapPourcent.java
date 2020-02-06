package fr.gustaveroussy.AdvancedQC;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class rempliMapPourcent {
	public Map<String, Double> remplirMapPourcent(List<Map<String, Double>> toutesMesMaps) {
		LOG.info("mon set de map"+ toutesMesMaps);
		Map<String, Double> mapMeanByGene = new HashMap<String,Double>();
		
		for (String geneKey : toutesMesMaps.get(0).keySet() ) {
        	DescriptiveStatistics stats = new DescriptiveStatistics ();
        	Iterator<Map<String, Double>> it = toutesMesMaps.iterator();
    		
			//itération dans la liste
	        while(it.hasNext()) { 		
	        	stats.addValue(it.next().get(geneKey) );
	        }
	        mapMeanByGene.put(geneKey, stats.getMean());    
		}
		 LOG.info("remplirMapPourcent : "+mapMeanByGene.toString());
		
		  return mapMeanByGene;		  
        }	
	private static Logger LOG = LoggerFactory
		      .getLogger(rempliMapPourcent.class);
}

