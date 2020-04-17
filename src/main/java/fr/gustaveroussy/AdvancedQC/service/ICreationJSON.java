package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;
import org.json.simple.JSONObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface ICreationJSON {
	JSONObject createJSON (List<SamplewHeader> listwHeader);
	
}