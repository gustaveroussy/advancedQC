package fr.gustaveroussy.AdvancedQC;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PourcentageTotal {
	

	public Map<String, Double> pourcenTotale (List<Map<String, Double>> toutesMesMaps) {
		
		Map<String,Double> desmaps = new HashMap<String,Double>();
double valinter =0;
Map<String,Double> desmapsbis = new HashMap<String,Double>();
		
		for (int i= 0; i < toutesMesMaps.size(); i++) {
			
			for (Double geneVal : toutesMesMaps.get(i).values()){	
				LOG.info("mes val" + geneVal);
			}
		}	
		
		
		
		//pourcentTotal(desmaps);
		return desmaps;
	
	
	}
	private double pourcentTotal(Map<String, Double> maMap) {
			 double pourcentageTotal = 0;
			 int counter = 0;
				for (Entry <String, Double> entry: maMap.entrySet()) {
					if(entry.getValue().equals(0.0)) {
						counter= counter+1;
					}
			 pourcentageTotal = (counter * 100)/maMap.size();		
			
				}
				 LOG.info("le pourcentage de valeurs nulles dans cet échantillon vaut: "+ pourcentageTotal + " %");
				 return pourcentageTotal;
	
//	private int Compteur(Map<String, Double> maMap) {
//		 int counter = 0;
//			for (Entry <String, Double> entry: maMap.entrySet()) {
//				if(entry.getValue().equals(0.0)) {
//				}
//			}
//			return counter;
				 
	}
	private static Logger LOG = LoggerFactory
		      .getLogger(AdvancedQcApplication.class);
	}


	
	

