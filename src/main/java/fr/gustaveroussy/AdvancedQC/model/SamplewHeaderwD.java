package fr.gustaveroussy.AdvancedQC.model;

import java.util.Map;

public class SamplewHeaderwD extends SamplewHeader{

	String sampleDesign;
	
	public SamplewHeaderwD(String sampleID,String sampleDesign, Map<String,Double> sampleGeneVal) {
		super(sampleID, sampleGeneVal);
		this.sampleDesign= sampleDesign;
	}
	
	public String getSampleCondition() {
		return sampleDesign;
	}
	
	@Override
	public String toString() {
		return this.sampleID + " " + this.sampleDesign +  " " ;
	}
	
}


