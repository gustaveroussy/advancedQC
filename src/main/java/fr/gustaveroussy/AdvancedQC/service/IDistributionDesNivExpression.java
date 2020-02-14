package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface IDistributionDesNivExpression {
	
	List<SampleValue> calculDecileMin (List<SamplewHeader> listwHeader);
	List<SampleValue> calculMediane(List<SamplewHeader> listwHeader);
	List<SampleValue> calculQuartileQ1 (List<SamplewHeader> listwHeader);
	

}
