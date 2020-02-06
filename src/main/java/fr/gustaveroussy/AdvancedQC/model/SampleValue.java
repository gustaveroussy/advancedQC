package fr.gustaveroussy.AdvancedQC.model;

public class SampleValue {
	String sampleName;
	Double percent;
	public SampleValue(String sampleName, Double percent) {
		super();
		this.sampleName = sampleName;
		this.percent = percent;
	}
	public String getSampleName() {
		return sampleName;
	}
	public Double getPercent() {
		return percent;
	}
	@Override
	public String toString() {
		return this.sampleName + " " + this.percent +  " %" ;
	}
	
}
