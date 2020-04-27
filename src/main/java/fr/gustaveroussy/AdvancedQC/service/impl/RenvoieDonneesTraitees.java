package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.IRenvoieDonnesTraitees;

public class RenvoieDonneesTraitees implements IRenvoieDonnesTraitees {
	
	@Override
	public List<SamplewHeader> renvoyerDonneesTraitees(List<String> lignesdefichier) {		
		List<SamplewHeader> listwHeader = new ArrayList<>();
        String spliter = "\t";
		String[] colonnewheader = lignesdefichier.get(0).split(spliter);
		lignesdefichier.remove(0);// elimination du header de la liste "lines"
			
		for (int i = 1; i < colonnewheader.length; i++) {
			Map<String, Double> maMap = changeListenTabExploitable(lignesdefichier, i, spliter);
					SamplewHeader sampleheader = new SamplewHeader(colonnewheader[i], maMap);
					listwHeader.add(sampleheader);		
			}	
		LOG.debug("Mes Maps:{} ", listwHeader);// renvoie les maps avec les valeurs d'origines et non les valeurs
									// calculées
		LOG.debug("taille de mesMaps:{} ",listwHeader.size());
		return listwHeader;
	}	
	private Map<String, Double> changeListenTabExploitable(List<String> lignesatransformer, Integer numechantillon, String spliter) throws IllegalArgumentException {
	Map<String,Double> maMap = new HashMap<String,Double>();
		for(String str : lignesatransformer){	
			 String[] toutesmescolonnes = str.split(spliter);	
			 if( numechantillon +1 >toutesmescolonnes.length) {
					throw new IllegalArgumentException("verifiez le fichier");
				}else {
				String valeurStringdufichier = toutesmescolonnes [numechantillon]; 
				double valeurDoubledufichier = Double.parseDouble (valeurStringdufichier);			
				maMap.put(toutesmescolonnes [0], valeurDoubledufichier);
				}
					
		}	
		LOG.debug( "map exploitable {}",maMap);
		return (maMap) ;
	}
	private static Logger LOG = LoggerFactory
	.getLogger(RenvoieDonneesTraitees.class);
}