package fr.gustaveroussy.AdvancedQC.service.impl;


import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public class PourcentageTotal {
	
//correction: remplacement de toutesMesMaps par listwHeader cad list des gene+val avec les headers 
	@SuppressWarnings("unchecked")
public JSONObject pourcenTotale (List<SamplewHeader> listwHeader) {
	 
		//List<SampleValue> sampleValueList = new ArrayList<SampleValue>();
        JSONObject percentList = new JSONObject();
        JSONObject percentEch =new JSONObject();
        
        
		for (int i= 0; i < listwHeader.size(); i++) {
			SamplewHeader samplewheader = listwHeader.get(i);
			LOG.debug("liste avec les headers {}", samplewheader);
			Double currPercent = pourcentTotal (samplewheader);
			LOG.debug("currPercent{}", currPercent);
			//SampleValue currSampleValue = new SampleValue(samplewheader.getSampleID(), currPercent);
			percentEch.put(samplewheader.getSampleID(), currPercent);
			LOG.debug("le pourcentage de val nul {}", percentEch);
	       // LOG.debug("le pourcentage de valeurs nulles pour {}", currSampleValue.toString());
		   //sampleValueList.add(currSampleValue);	
			percentList.putAll(percentEch);
		}
		LOG.debug("liste de % {}", percentList);
		return percentList;		
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
	


	
	

