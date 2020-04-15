package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationBargraphJSON;

public class CreationBargraphJSON implements ICreationBargraphJSON {

//CREATION DU FICHIER JSON	
	@SuppressWarnings("unchecked")
	public JSONObject createBrGJSON(List<SamplewHeader> listwHeader, String path) {
		
		JSONObject percentotal = pourcenTotale(listwHeader);
		JSONObject fichierJSONfinal = new JSONObject();
		
		// construction du bloc header
		JSONObject header1 = new JSONObject();
		JSONObject pconfigComplet = new JSONObject();// =2nde partie du header, permet d'avoir la bonne mise en forme
														// pour pconfig
		JSONObject pconfig1 = new JSONObject();// info brute de pconfig

		header1.put("id", "bargraph_test");
		header1.put("section_name", "Bargraph");
		header1.put("plot_type", "bargraph");
		LOG.debug("header1{}", header1);

		pconfig1.put("id", "custom data json bargraph");
		pconfig1.put("title", " percent val nul");
		pconfig1.put("ylab", "Number");
		LOG.debug("pconfig{}", pconfig1);

		pconfigComplet.put("pconfig", pconfig1);
		LOG.debug("pconfig{}", pconfigComplet);

		// construction du bloc data
		JSONObject data = new JSONObject();
		JSONObject percentData = new JSONObject();
		percentData.putAll(percentotal);

		data.put("data", percentData);
		LOG.debug("data bargraph{}", data);

//ensemble des données constituant le fichier json cad header+data
		fichierJSONfinal.putAll(header1);
		fichierJSONfinal.putAll(pconfigComplet);
		fichierJSONfinal.putAll(data);

		LOG.info("fichierJSONfinale {}", fichierJSONfinal);

		// Write JSON file
		try (FileWriter file = new FileWriter(path)) {
			file.write(fichierJSONfinal.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fichierJSONfinal;
	}

	// CALCUL DU POURCENTAGE
	@SuppressWarnings({ "unchecked" })
	private JSONObject pourcenTotale(List<SamplewHeader> listwHeader) {
		// List<SampleValue> sampleValueList = new ArrayList<SampleValue>();
		JSONObject percentList = new JSONObject(); // remplace sampleValueList
		for (int i = 0; i < listwHeader.size(); i++) {
			JSONObject currsampl = new JSONObject();// remplace currSampleValue key=samplewheader.getSampleID,
													// val=samplepercent
			JSONObject samplepercent = new JSONObject();// key= "pourcentage val nul", val =percent

			SamplewHeader samplewheader = listwHeader.get(i);
			LOG.debug("liste avec les headers {}", samplewheader);
			Double currPercent = pourcentTotal(samplewheader);
			LOG.debug("currPercent{}", currPercent);
			samplepercent.put("poucentage valeurs nulle", currPercent);
			LOG.debug("samplepercent{}", samplepercent);
			currsampl.put(samplewheader.getSampleID(), samplepercent);
			LOG.debug("le pourcentage de val nul {}", currsampl);
			percentList.putAll(currsampl);// add"
		}
		LOG.info("liste de % {}", percentList);
		return percentList;
	}

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

	private static Logger LOG = LoggerFactory.getLogger(CreationBargraphJSON.class);
}