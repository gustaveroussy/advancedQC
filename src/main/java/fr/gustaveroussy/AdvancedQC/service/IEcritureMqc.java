package fr.gustaveroussy.AdvancedQC.service;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IEcritureMqc {
	JsonElement ecritureMqc (JsonElement filemqc, String absolutePath) throws IOException;
	
}
