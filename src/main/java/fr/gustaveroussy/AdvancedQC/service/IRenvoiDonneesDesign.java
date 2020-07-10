package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;

public interface IRenvoiDonneesDesign {
	
	List<SamplewHeaderwD>renvoyerDonneesDesign(List<String> lignesdefichierDsg, List<SamplewHeader> listwh);

}
