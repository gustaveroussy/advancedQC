package fr.gustaveroussy.AdvancedQC.model;

import java.util.Map;

public class SamplewHeaderwD extends SamplewHeader{

	Map<String, String> sampleDesign;
	
	public SamplewHeaderwD(String sampleID,Map <String, String> sampleDesign, Map<String,Double> sampleGeneVal) {
		super(sampleID, sampleGeneVal);
		this.sampleDesign= sampleDesign;
	}
	
	public Map<String, String> getSampleCondition() {
		return sampleDesign;
	}
	
	@Override
	public String toString() {
		return this.sampleID + " " + this.sampleDesign +  " " ;
	}
	
}


