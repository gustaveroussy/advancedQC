package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureFiles;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Service
public class CreationBGjson implements ICreationJSON {
	@Autowired
	IEcritureFiles ecritureMqc;


	// CREATION BARGRAPH
	@Override
	public JsonObject createJSON(List<? extends SamplewHeader> listwHeader) {
		
		// ensemble des données constituant le fichier json cad header+data
		JsonObject bgJSON = new JsonObject();
		// construction du bloc header
		
		bgJSON.addProperty("id", "bargraph");
		bgJSON.addProperty("section_name", "Percentage of null values");
		bgJSON.addProperty("plot_type", "bargraph");
		
		JsonObject pconfigBG =new JsonObject();
		pconfigBG.addProperty("id", "custom data json bargraph");
		pconfigBG.addProperty("title", " counting null values");
		pconfigBG.addProperty("ylab", "Number");
		LOG.debug("pconfig{}", pconfigBG);

		bgJSON.add("pconfig", pconfigBG);
		

		// construction du bloc data BG
		bgJSON.add("data", percenTotal(listwHeader));

		

		LOG.debug("fichierJSONfinale {}", bgJSON);

		return bgJSON;
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
		 Double x = 0.0;//prise en compte de valeur null ou égale à zéro
		Map<String, Double> maMap = samplewheader.getSampleGeneVal();
		for (Entry<String, Double> entry : maMap.entrySet()) {
			
			if (entry.getValue().equals(x)) {
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

	@Override
	public  void export(String filePath, List<? extends SamplewHeader> listwHeader) throws IOException {
		JsonElement filemqc = this.createJSON(listwHeader);
		ecritureMqc.ecritureMqc(filemqc,
				filePath + File.separator+this.getClass().getSimpleName().concat("_mqc.json"));
		LOG.debug("filemqc{}", filemqc);
	}
	
	
}


