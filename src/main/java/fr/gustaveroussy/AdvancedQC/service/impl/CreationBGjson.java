package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class CreationBGjson implements ICreationJSON {


	// CREATION BARGRAPH
	@Override
	public JsonObject createJSON(List<? extends SamplewHeader> listwHeader) {
		
		// ensemble des données constituant le fichier json cad header+data
		JsonObject bgJSONfinal = new JsonObject();
		// construction du bloc header
		
		bgJSONfinal.addProperty("id", "bargraph");
		bgJSONfinal.addProperty("section_name", "Bargraph");
		bgJSONfinal.addProperty("plot_type", "bargraph");
		
		JsonObject pconfigBG =new JsonObject();
		pconfigBG.addProperty("id", "custom data json bargraph");
		pconfigBG.addProperty("title", " counting null values");
		pconfigBG.addProperty("ylab", "Number");
		LOG.debug("pconfig{}", pconfigBG);

		bgJSONfinal.add("pconfig", pconfigBG);
		

		// construction du bloc data BG
		bgJSONfinal.add("data", percenTotal(listwHeader));

		

		LOG.debug("fichierJSONfinale {}", bgJSONfinal);

		return bgJSONfinal;
	}

	// creation d'une liste avec nom de l'echantillon et le pourcentage de valeur
	// nulle associé
	private JsonElement percenTotal(List<? extends SamplewHeader> listwHeader) {
		Gson gSONparser = new Gson();
		JsonObject percentList = new JsonObject();
		// for (int i = 0; i < listwHeader.size(); i++) {
		for (SamplewHeader samplewheader : listwHeader) {
			LinkedHashMap<String, Double> samplepercent = new LinkedHashMap<String, Double>();// key= pourcentage val nul et val =percent
			LOG.debug("liste avec les headers {}", samplewheader);
			Double currPercent = countPercValNul(samplewheader);
			double nonNulPercent = 100 - currPercent;
			LOG.debug("currPercent{}", currPercent);
			String label = "null values";
			if(samplewheader instanceof SamplewHeaderwD) {
				SamplewHeaderwD samplewheaderwd = (SamplewHeaderwD)samplewheader;
				label = label.concat(" ").concat( samplewheaderwd.getSampleCondition().toString() );//ajout condition ds la légende
			}
			samplepercent.put("non null values", nonNulPercent);
			samplepercent.put(label, currPercent);
			
			LOG.debug("samplepercent{}", samplepercent);
			JsonElement currsamplJSON = gSONparser.toJsonTree(samplepercent, TreeMap.class);
			LOG.debug("currsampleJSON{}", currsamplJSON);
			percentList.add(samplewheader.getSampleID(), currsamplJSON);
		}
		LOG.debug("liste de % {}", percentList);
		return percentList;
	}
		
	// Méthode comptage des valeurs nulles et calcul du pourcentage de celles-ci
	// dans l'echantillon
	private double countPercValNul(SamplewHeader samplewheader) {
		double counter = 0;
		Map<String, Double> maMap = samplewheader.getSampleGeneVal();
		for (Entry<String, Double> entry : maMap.entrySet()) {
			if (entry.getValue().equals(0.0)) {
				counter = counter + 1;
			}
		}
		double result = (counter * 100) / maMap.size();

		LOG.debug("result{}", result);
		LOG.debug("compteur {},", counter);
		LOG.debug("mamap.size {}", maMap.size());
		return result;
	}

	private static Logger LOG = LoggerFactory.getLogger(CreationBGjson.class);
}


