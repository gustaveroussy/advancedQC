package fr.gustaveroussy.AdvancedQC.model;

public class SampleValue {
	String sampleName;
	Double sampleVal;
	public SampleValue(String sampleName, Double percent) {
		super();
		this.sampleName = sampleName;
		this.sampleVal = percent;
	}
	public String getSampleName() {
		return sampleName;
	}
	public Double getPercent() {
		return sampleVal;
	}
	@Override
	public String toString() {
		return this.sampleName + " " + this.sampleVal +  " " ;
	}
	
}
