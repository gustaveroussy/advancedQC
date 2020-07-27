package fr.gustaveroussy.AdvancedQC.service;

import java.io.IOException;
import java.util.List;
import com.google.gson.JsonElement;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface ICreationJSON {
	JsonElement createJSON(List<? extends SamplewHeader> listwHeader);
	
	void export(String filePath, List<? extends SamplewHeader> listwHeader) throws IOException;

	
}