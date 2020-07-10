package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.List;
import org.json.simple.JSONObject;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;

public class CreationBGwDJSON extends CreationBGjson{
	List <SamplewHeaderwD> listwhwd;
	List<SamplewHeader> listwHeader;
	@Override
	public 	JSONObject createwDJSON (List <SamplewHeaderwD> listwhwd) {
		super.createJSON(listwHeader);
		return null ;
  }
}