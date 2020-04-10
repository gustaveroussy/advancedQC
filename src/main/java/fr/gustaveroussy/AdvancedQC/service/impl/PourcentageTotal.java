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
        JSONObject percentList = new JSONObject(); //remplace sampleValueList
        JSONObject currsampl =new JSONObject();// remplace currSampleValue key=samplewheader.getSampleID, val=samplepercent
        JSONObject samplepercent = new JSONObject();//key= "pourcentage val nul", val =percent 
        
        
        
		for (int i= 0; i < listwHeader.size(); i++) {
			SamplewHeader samplewheader = listwHeader.get(i);
			LOG.debug("liste avec les headers {}", samplewheader);
			Double currPercent = pourcentTotal (samplewheader);
			LOG.debug("currPercent{}", currPercent);
			samplepercent.put("poucentage valeurs nulle", currPercent);
			LOG.debug("samplepercent{}", samplepercent);
			currsampl.put(samplewheader.getSampleID(), samplepercent);
			LOG.debug("le pourcentage de val nul {}", currsampl);
	      
			percentList.putAll(currsampl);// la liste ne contient qu'un des pourcentages suite à intriduction de "putAll" a la place de "add"
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
	


	
	

