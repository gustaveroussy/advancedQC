package fr.gustaveroussy.AdvancedQC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class renvoieDonneesTraitees {
	
	public List<Map<String,Double>> renvoyerDonneesTraitees (List<String> lignesdefichier){
		List<Map<String, Double>> toutesMesMaps = new ArrayList<Map<String, Double>>();
		
	Map<String,Double> maMap = null;
	Map<String,Map<String,Double>>mapheader = null;
	lignesdefichier.remove(0); //elimination du header de la liste "lines"
	
	
		 String[] toutesmescolonnes = lignesdefichier.get(0).split("\t");
		
			 
			for (int i = 1; i<toutesmescolonnes.length; i++) {
				maMap = changeListenTabExploitable(lignesdefichier, i);	
			    toutesMesMaps.add(maMap);	
		 }
			for(int j=0; j<toutesmescolonnes.length;j++) {  	
		    	mapheader.put(toutesmescolonnes[j],maMap);
		    }
		  LOG.debug ( "Mes Maps: " + toutesMesMaps);//renvoie les maps avec les valeurs d'origines et non les valeurs calculées
		  LOG.debug ( "taille de mesMaps: " + toutesMesMaps.size());
			return (toutesMesMaps);
	}
	
	private Map<String, Double> changeListenTabExploitable(List<String> lignesatransformer, Integer numechantillon) {
	Map<String,Double> maMap = new HashMap<String,Double>();
		for(String str : lignesatransformer){	
			 String[] toutesmescolonnes = str.split("\t");			
				String valeurStringdufichier = toutesmescolonnes [numechantillon]; 
				double valeurDoubledufichier = Double.parseDouble (valeurStringdufichier);	
				maMap.put(toutesmescolonnes [0], valeurDoubledufichier);
		}
		LOG.debug( "map exploitable"+maMap);
		return (maMap) ;
		}



	private static Logger LOG = LoggerFactory
	.getLogger(renvoieDonneesTraitees.class);

}