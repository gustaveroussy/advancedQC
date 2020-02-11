package fr.gustaveroussy.AdvancedQC.model;

public class SampleValue {
	String sampleName;
	Double SampleValue;
	public SampleValue(String sampleName, Double sampleValue) {
		super();
		this.sampleName = sampleName;
		this.SampleValue = sampleValue;
	}
	public String getSampleName() {
		return sampleName;
	}
	public Double getSampleValue() {
		return SampleValue;
	}
	@Override
	public String toString() {
		return this.sampleName + " " + this.SampleValue +  " " ;
	}
	
}
