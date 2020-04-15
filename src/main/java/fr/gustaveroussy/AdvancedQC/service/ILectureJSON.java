package fr.gustaveroussy.AdvancedQC.service;

import java.io.IOException;

import org.json.simple.JSONObject;

public interface ILectureJSON {
	JSONObject lectureJSON (JSONObject fichierJSONfinal, String pathAbsolute) throws IOException;
	
}
