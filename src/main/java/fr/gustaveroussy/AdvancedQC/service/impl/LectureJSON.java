package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.service.ILectureJSON;

public class LectureJSON implements ILectureJSON {

	@SuppressWarnings("unchecked")
	public void LectureJSON ( ) {
		// JSON parser object to parse read file
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(
				"/Users/hadidjasaid/Documents/GitHub/advancedQC/MultiQC_file/beeswarm_quantil_mqc.json")) {
			// Lecture fichier JSON
			Object obj = jsonParser.parse(reader); 

			JSONArray distribNivExpJSON = (JSONArray) obj;
			LOG.info("{} ", distribNivExpJSON);

			// Iterate over distribNivExpJSON array
			distribNivExpJSON.forEach(emp -> parseQuantilObject((JSONObject) emp));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	}

	private static void parseQuantilObject(JSONObject quantil) // quantil correspond à D1/D9/Q1/Q3/med ds fichier json
	{
		// Get quantil object within list
		JSONObject quantilObject = (JSONObject) quantil.get("quantil");

		// Get sampleheader, le nom du sample
		String header = (String) quantilObject.get("header");
		LOG.info("header {}", header);

		// Get percentile, valeur numerique du quantile
		Double percentile = (Double) quantilObject.get("percentile");
		LOG.info("percentile {}", percentile);

	}

	private static Logger LOG = LoggerFactory.getLogger(LectureJSON.class);

}
