package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;

public class CreationBGjson implements ICreationJSON{
	
	//CREATION BARGRAPH	
		@SuppressWarnings("unchecked")
		public JSONObject createJSON(List<SamplewHeader> listwHeader) {

			JSONObject percentotal = pourcenTotale(listwHeader);
			// construction du bloc header
			JSONObject headerBG = new JSONObject();
			JSONObject pconfigCompletBG = new JSONObject();
			JSONObject pconfigBG1 = new JSONObject();

			headerBG.put("id", "bargraph_test");
			headerBG.put("section_name", "Bargraph");
			headerBG.put("plot_type", "bargraph");
			LOG.debug("header1{}", headerBG);

			pconfigBG1.put("id", "custom data json bargraph");
			pconfigBG1.put("title", " percent val nul");
			pconfigBG1.put("ylab", "Number");
			LOG.debug("pconfig{}", pconfigBG1);

			pconfigCompletBG.put("pconfig", pconfigBG1);
			LOG.debug("pconfig{}", pconfigCompletBG);

			// construction du bloc data BG
			JSONObject data = new JSONObject();
			JSONObject percentData = new JSONObject();
			percentData.putAll(percentotal);

			data.put("data", percentData);
			LOG.debug("data bargraph{}", data);

			// ensemble des données constituant le fichier json cad header+data
			JSONObject bgJSONfinal = new JSONObject();
			bgJSONfinal.putAll(headerBG);
			bgJSONfinal.putAll(pconfigCompletBG);
			bgJSONfinal.putAll(data);

			LOG.debug("fichierJSONfinale {}", bgJSONfinal);

			return bgJSONfinal;
		}

		// creation d'une liste avec nom de l'echantillon et le pourcentage de valeur nulle associé
		@SuppressWarnings({ "unchecked" })
		private JSONObject pourcenTotale(List<SamplewHeader> listwHeader) {

			JSONObject percentList = new JSONObject();
			//for (int i = 0; i < listwHeader.size(); i++) {
				for (SamplewHeader samplewheader : listwHeader) {
				JSONObject currsampl = new JSONObject();// key=samplewheader.getSampleID et val=samplepercent
				JSONObject samplepercent = new JSONObject();// key= pourcentage val nul et val =percent
				LOG.debug("liste avec les headers {}", samplewheader);
				Double currPercent = pourcentTotal(samplewheader);
				LOG.debug("currPercent{}", currPercent);
				samplepercent.put("poucentage valeurs nulle", currPercent);
				LOG.debug("samplepercent{}", samplepercent);
				currsampl.put(samplewheader.getSampleID(), samplepercent);
				LOG.debug("le pourcentage de val nul {}", currsampl);
				percentList.putAll(currsampl);// add"
			}
			LOG.debug("liste de % {}", percentList);
			return percentList;
		}

		// Méthode comptage des valeurs nulles et calcul du pourcentage de celles-ci dans l'echantillon
		private double pourcentTotal(SamplewHeader samplewheader) {
			int counter = 0;
			Map<String, Double> maMap = samplewheader.getSampleGeneVal();
			for (Entry<String, Double> entry : maMap.entrySet()) {
				if (entry.getValue().equals(0.0)) {
					counter = counter + 1;
				}
			}
			LOG.debug("compteur {},", counter);
			LOG.debug("mamap.size {}", maMap.size());
			return (counter * 100) / maMap.size();
		}
		private static Logger LOG = LoggerFactory.getLogger(CreationBGjson.class);
}

