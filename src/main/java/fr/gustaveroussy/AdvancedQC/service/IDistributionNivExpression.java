package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;

import org.json.simple.JSONObject;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface IDistributionNivExpression {
	
	JSONObject calculDecileMin(List<SamplewHeader> listwHeader);
	JSONObject calculDecileMax(List<SamplewHeader> listwHeader);
	JSONObject calculMediane(List<SamplewHeader> listwHeader);
	JSONObject calculQ1(List<SamplewHeader> listwHeader);
	JSONObject  calculQ3(List<SamplewHeader> listwHeader);
	

}
