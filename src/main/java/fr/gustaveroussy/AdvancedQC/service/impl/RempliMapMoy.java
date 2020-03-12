package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;


import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

 public class RempliMapMoy {


	public List<SampleValue> geneExpressionMean (List<SamplewHeader> listwHeader ) {
	List<SampleValue> geneExpressionList = new ArrayList<SampleValue>();
	SampleValue geneExpression = null;
	
 	for (String geneKey : listwHeader.get(0).getSampleGeneVal().keySet() ) {
     	DescriptiveStatistics stats = new DescriptiveStatistics ();
     	Iterator<SamplewHeader> it = listwHeader.iterator();
		//itération dans la liste
     while(it.hasNext()) { 		
     	stats.addValue(it.next().getSampleGeneVal().get(geneKey) );
      geneExpression= new SampleValue(geneKey, stats.getMean());
     }
     geneExpressionList.add(geneExpression);
	}
 	LOG.debug("liste des tx d'expression des genes:{} " ,geneExpressionList);
 	return geneExpressionList;
}
	private static Logger LOG = LoggerFactory
		      .getLogger(RempliMapMoy.class);
}
