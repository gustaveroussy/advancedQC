package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.IRenvoiDonneesDesign;


public class RenvoiDonneesDesign implements IRenvoiDonneesDesign {
 
	@Override
	public List<SamplewHeaderwD> renvoyerDonneesDesign (List <String> lignefichierDesign){
	List<SamplewHeaderwD> listwheaderwd = new ArrayList<>();
    String spliter = "\t";
	String[] colonnewheaderwd = lignefichierDesign.get(1).split(spliter);
	lignefichierDesign.remove(0);
	
	
	for (int i = 1; i < colonnewheaderwd.length; i++) {
	SamplewHeaderwD samplewheaderwd = new SamplewHeaderwD (colonnewheaderwd[2], colonnewheaderwd[3], null);
	listwheaderwd.add(samplewheaderwd);
	}
	LOG.debug("listwheaderwd", listwheaderwd);
	return listwheaderwd;
}
	
	private static Logger LOG = LoggerFactory
    .getLogger(RenvoiDonneesDesign.class);
}