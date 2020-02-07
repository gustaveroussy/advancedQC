package fr.gustaveroussy.AdvancedQC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;

public class PourcentageTotal {
	

	public List<SampleValue> pourcenTotale (List<Map<String, Double>> toutesMesMaps) {
		
		List<SampleValue> sampleValueList = new ArrayList<SampleValue>();


		for (int i= 0; i < toutesMesMaps.size(); i++) {
			LOG.debug("map "+ toutesMesMaps.get(i));
			Double currPercent = pourcentTotal(toutesMesMaps.get(i));

	        
			//FIXME faire remonter le nom de l'échantillon dans la structure de donnée
			SampleValue currSampleValue = new SampleValue("sampleName"+i, currPercent);
	        LOG.debug("le pourcentage de valeurs nulles pour "+ currSampleValue.toString());
			sampleValueList.add(currSampleValue);
			
		}
		return sampleValueList;
	}
	
	
	private double pourcentTotal(Map<String, Double> maMap) {
			int counter = 0;
			for (Entry <String, Double> entry: maMap.entrySet()) {
				if(entry.getValue().equals(0.0)) {
					counter= counter+1;
				}
			}			
			return (counter * 100)/maMap.size();				 
	}
	
	private static Logger LOG = LoggerFactory
		      .getLogger(PourcentageTotal.class);
	}


	
	

