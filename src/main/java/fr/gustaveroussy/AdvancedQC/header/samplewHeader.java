package fr.gustaveroussy.AdvancedQC.header;

import java.util.Map;

public class samplewHeader {
	String sampleID;
	Map<String,Double> sampleGeneVal;
	public samplewHeader(String sampleID, Map<String,Double> sampleGeneVal) {
		super();
		this.sampleID = sampleID;
		this.sampleGeneVal = sampleGeneVal;
	}

	
}
