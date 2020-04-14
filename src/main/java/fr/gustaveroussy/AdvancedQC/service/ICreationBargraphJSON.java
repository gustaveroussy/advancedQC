package fr.gustaveroussy.AdvancedQC.service;

import org.json.simple.JSONObject;

public interface ICreationBargraphJSON {
	JSONObject createBrGJSON (JSONObject percentlist, String path);
}