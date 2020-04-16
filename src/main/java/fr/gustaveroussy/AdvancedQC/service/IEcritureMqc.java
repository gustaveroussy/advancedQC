package fr.gustaveroussy.AdvancedQC.service;

import java.io.IOException;

import org.json.simple.JSONObject;

public interface IEcritureMqc {
	JSONObject ecritureMqc (JSONObject fichierJSONfinal, String pathAbsolute) throws IOException;
	
}
