package fr.gustaveroussy.AdvancedQC;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class rempliMapPourcent {
	public Map<String, Double> remplirMapPourcent(List<Map<String, Double>> toutesMesMaps) {
		System.out.println("mon set de map"+ toutesMesMaps);
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
		 System.out.println("remplirMapPourcent : "+mapMeanByGene.toString());
		
		  return mapMeanByGene;		  
        }	
}

