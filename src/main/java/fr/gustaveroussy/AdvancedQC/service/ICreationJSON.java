package fr.gustaveroussy.AdvancedQC.service;

import java.io.IOException;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface ICreationJSON {
	JsonElement createJSON(List<? extends SamplewHeader> listwHeader);
	
	void export(String filePath, List<SamplewHeader> listwHeader) throws IOException;

	
}