package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface IDistributionNivExpression {
	
	List<SampleValue> calculDecileMin(SamplewHeader sample);
	List<SampleValue> calculDecileMax(SamplewHeader sample);
	List<SampleValue> calculMediane(SamplewHeader sample);
	List<SampleValue> calculQ1(SamplewHeader sample);
	List<SampleValue> calculQ3(SamplewHeader sample);
	

}
