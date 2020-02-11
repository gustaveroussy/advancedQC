package fr.gustaveroussy.AdvancedQC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;

 public class RempliMapMoy {
//	public Map<String, Double> remplirMapPourcent(List<Map<String, Double>> toutesMesMaps) {
//		LOG.debug("mon set de map"+ toutesMesMaps);
//		Map<String, Double> mapMeanByGene = new HashMap<String,Double>();
//		
//		for (String geneKey : toutesMesMaps.get(0).keySet() ) {
//        	DescriptiveStatistics stats = new DescriptiveStatistics ();
//        	Iterator<Map<String, Double>> it = toutesMesMaps.iterator();
//    		
//			//itération dans la liste
//	        while(it.hasNext()) { 		
//	        	stats.addValue(it.next().get(geneKey) );
//	        }
//	        mapMeanByGene.put(geneKey, stats.getMean());    
//		}
//		 LOG.debug("remplirMapPourcent : "+mapMeanByGene.toString());
//		
//		  return mapMeanByGene;		  
//        }	

	
	
	//modif pour rendre le code plus facilement lisible: en entrée liste de maps et en sortir liste gene/mynn des tx expression
	 
	public List<SampleValue> geneExpressionMean (List<Map<String, Double>> toutesMesMaps) {
	List<SampleValue> geneExpressionList = new ArrayList<SampleValue>();
	SampleValue geneExpression = null;
	
    	for (String geneKey : toutesMesMaps.get(0).keySet() ) {
        	DescriptiveStatistics stats = new DescriptiveStatistics ();
        	Iterator<Map<String, Double>> it = toutesMesMaps.iterator();
		//itération dans la liste
        while(it.hasNext()) { 		
        	stats.addValue(it.next().get(geneKey) );
         geneExpression= new SampleValue(geneKey, stats.getMean());
        }
        geneExpressionList.add(geneExpression);
	}
    	LOG.debug("liste des tx d'expression des genes: " +geneExpressionList);
    	return geneExpressionList;
}
	private static Logger LOG = LoggerFactory
		      .getLogger(RempliMapMoy.class);
}

