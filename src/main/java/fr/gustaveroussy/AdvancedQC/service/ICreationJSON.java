package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;
import org.json.simple.JSONObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface ICreationJSON {
	JSONObject createBWJSON (List<SamplewHeader> listwHeader);
	JSONObject createBGJSON(List<SamplewHeader> listwHeader);
}