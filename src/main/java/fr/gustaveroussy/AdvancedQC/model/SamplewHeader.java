package fr.gustaveroussy.AdvancedQC.model;

import java.util.Map;

public class SamplewHeader {
	String sampleID;
	Map<String,Double> sampleGeneVal;
	
	public SamplewHeader(String sampleID, Map<String,Double> sampleGeneVal) {
		super();
		this.sampleID = sampleID;
		this.sampleGeneVal = sampleGeneVal;
	}
	
	public String getSampleID() {
		return sampleID;
	}
	public Map<String, Double> getSampleGeneVal() {
		return sampleGeneVal;
	}
		@Override
		public String toString() {
			return this.sampleID + " " + this.sampleGeneVal +  " " ;
		}
	
}
