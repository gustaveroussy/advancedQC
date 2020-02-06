package fr.gustaveroussy.AdvancedQC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PourcentageTotal {
	

	public Map<String, Double> pourcenTotale (List<Map<String, Double>> toutesMesMaps) {
		
		Map<String,Double> elmtDeMaListeDemaps = new HashMap<String,Double>();



		for (int i= 0; i < toutesMesMaps.size(); i++) {
			elmtDeMaListeDemaps= toutesMesMaps.get(i);
	        LOG.info("map "+ elmtDeMaListeDemaps);
	        pourcentTotal(elmtDeMaListeDemaps);
		}
		return elmtDeMaListeDemaps;
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
	
				 
	}
	private static Logger LOG = LoggerFactory
		      .getLogger(PourcentageTotal.class);
	}


	
	

