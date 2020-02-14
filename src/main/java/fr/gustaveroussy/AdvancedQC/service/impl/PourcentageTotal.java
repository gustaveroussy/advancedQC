package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public class PourcentageTotal {
	
//correction: remplacement de toutesMesMaps par listwHeader cad list des gene+val avec les headers 
public List<SampleValue> pourcenTotale (List<SamplewHeader> listwHeader) {
		
		List<SampleValue> sampleValueList = new ArrayList<SampleValue>();


		for (int i= 0; i < listwHeader.size(); i++) {
			SamplewHeader samplewheader = listwHeader.get(i);
			LOG.debug("liste avec les headers {}", samplewheader);
			Double currPercent = pourcentTotal (samplewheader);
			LOG.debug("currPercent{}", currPercent);
			SampleValue currSampleValue = new SampleValue(samplewheader.getSampleID(), currPercent);
			//SamplewHeader currSamplewheader = new SampleValue(currSamplewheader.getSampleID(i), currPercent));
	        LOG.debug("le pourcentage de valeurs nulles pour {}", currSampleValue.toString());
			sampleValueList.add(currSampleValue);
			
		}
		return sampleValueList;
		
	}




	private double pourcentTotal(SamplewHeader samplewheader) {
		int counter = 0;
		Map<String, Double> maMap = samplewheader.getSampleGeneVal();
		for (Entry<String, Double> entry :maMap.entrySet() ) {
			if (entry.getValue().equals(0.0)) {
				counter = counter + 1;
			}
		}
	LOG.debug("compteur {},",counter);
	LOG.debug("mamap.size {}",maMap.size());
		return (counter * 100) / maMap.size();
	}

	private static Logger LOG = LoggerFactory.getLogger(PourcentageTotal.class);
}
	


	
	
