package fr.gustaveroussy.AdvancedQC.service;

import org.json.simple.JSONObject;

public interface ICreationBeeswarmJSON {
	JSONObject createBWJSON (JSONObject Q1, JSONObject Q2, JSONObject Q3,JSONObject Q4,JSONObject Q5, String path );
}