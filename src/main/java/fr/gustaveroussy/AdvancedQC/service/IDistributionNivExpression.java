package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface IDistributionNivExpression {
	
	List<SampleValue> calculDecileMin(List<SamplewHeader> listwHeader);
	List<SampleValue> calculDecileMax(List<SamplewHeader> listwHeader);
	List<SampleValue> calculMediane(List<SamplewHeader> listwHeader);
	List<SampleValue> calculQ1(List<SamplewHeader> listwHeader);
	List<SampleValue> calculQ3(List<SamplewHeader> listwHeader);
	

}
