package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;

import com.google.gson.JsonObject;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface ICreationJSON {
	JsonObject createJSON(List<? extends SamplewHeader> listwHeader);
	
	}