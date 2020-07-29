package fr.gustaveroussy.AdvancedQC.service;

import java.io.IOException;
import com.google.gson.JsonElement;

public interface IEcritureFiles {
	JsonElement ecritureMqc(JsonElement fichierJSON, String absolutePath) throws IOException ;
	JsonElement ecriturePlotly(JsonElement fichierJSON, String absolutePath) throws IOException ;
}
