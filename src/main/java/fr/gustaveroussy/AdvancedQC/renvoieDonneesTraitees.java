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
		transformListenTabExploitable transformeMonFichier = new transformListenTabExploitable();
		
	Map<String,Double> maMap = null;
	lignesdefichier.remove(0); //elimination du header de la liste "lines"
		 String[] toutesmescolonnes = lignesdefichier.get(0).split("\t");	
			for (int i = 1; i<toutesmescolonnes.length; i++) {
				maMap = transformeMonFichier.changeListenTabExploitable(lignesdefichier, i);	
			    toutesMesMaps.add(maMap);		    
			}
		  LOG.debug ( "Mes Maps: " + toutesMesMaps);//renvoie les maps avec les valeurs d'origine et non les valeurs calculées
		  LOG.debug ( "taille de mesMaps: " + toutesMesMaps.size());
			return (toutesMesMaps);
	}



public class transformListenTabExploitable {
	
	public Map<String, Double> changeListenTabExploitable(List<String> lignesatransformer, Integer numechantillon) {
		// TODO Auto-generated method stub
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

}

	private static Logger LOG = LoggerFactory
	.getLogger(renvoieDonneesTraitees.class);

}