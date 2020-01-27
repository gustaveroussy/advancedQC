package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class AdvancedQcApplication implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory
		      .getLogger(AdvancedQcApplication.class);
	
	public static void main(String[] args) {
		LOG.info("Hello World ");
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Bye Bye World");
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		if(args.length == 1) {
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);  
	
		
			renvoieDonneesTraitees(lines);
		}else {
			LOG.error("args must be 1");
		}	
		
	}
	
	
//public Set<Map<String,Double>> renvoieDonneesTraitees (List<String> lignesdefichier){
//		 // TODO Auto-generated method stub
//	Set<Map<String, Double>> toutesMesMaps = new HashSet<Map<String, Double>>();
//	
//	Map<String,Double> maMap = null;
//	lignesdefichier.remove(0); //elimination du header de la liste "lines"
//		 String[] toutesmescolonnes = lignesdefichier.get(0).split("\t");	
//			for (int i = 1; i<toutesmescolonnes.length; i++) {
//				maMap=transformListenTabExploitable(lignesdefichier, i);	
//				pourcentageTotal (maMap);
//				LOG.info("Map de l'echantillon "+ i + ": "+maMap.toString());
//			    remplirMapPourcent(maMap);
//			    toutesMesMaps.add(maMap);
//			    
//			}
//		  LOG.info ( "Mes Maps: " + toutesMesMaps);//renvoie les maps avec les valeurs d'origine et non les valeurs calculées
//	
//			return (toutesMesMaps);
//	}
	

//****************Test modif: RENVOIE DONNEES TRAITEES, pour obtenir un set de données qui sera réutiliser par les autre parties du prgrm
	
public Set<Map<String,Double>> renvoieDonneesTraitees (List<String> lignesdefichier){
	 // TODO Auto-generated method stub
Set<Map<String, Double>> toutesMesMaps = new HashSet<Map<String, Double>>();

Map<String,Double> maMap = null;
lignesdefichier.remove(0); //elimination du header de la liste "lines"
	 String[] toutesmescolonnes = lignesdefichier.get(0).split("\t");	
		for (int i = 1; i<toutesmescolonnes.length; i++) {
			maMap=transformListenTabExploitable(lignesdefichier, i);	
		    toutesMesMaps.add(maMap);		    
		}
	  LOG.info ( "Mes Maps: " + toutesMesMaps);//renvoie les maps avec les valeurs d'origine et non les valeurs calculées
	  LOG.info ( "taille de mesMaps: " + toutesMesMaps.size());
		return (toutesMesMaps);
}

	
//Méthode pour créer, à partir du fichier .tsv, un tableau de données exploitable
	
public Map<String, Double> transformListenTabExploitable(List<String> lignesatransformer, Integer numechantillon) {
		// TODO Auto-generated method stub
	Map<String,Double> maMap = new HashMap<String,Double>();
		for(String str : lignesatransformer){	
			 String[] toutesmescolonnes = str.split("\t");			
//				LOG.info("Mes lignes [0]" +toutesmeslignes[0]);// renvoie bien le nom de tout les genes
				String valeurStringdufichier = toutesmescolonnes [numechantillon]; 
				double valeurDoubledufichier = Double.parseDouble (valeurStringdufichier);	
				maMap.put(toutesmescolonnes [0], valeurDoubledufichier);
		}
		
		return (maMap) ;
		}




//Methode pour compter les valeurs nulles par échantillon
 
	private int Compteur (Map <String,Double> maMap) {
		 int Counter = 0;
		// TODO Auto-generated method stub
		for (Entry <String, Double> entry: maMap.entrySet()) {
			if(entry.getValue().equals(0.0)) {
				Counter = Counter+1 ;
			}
		}
		LOG.info ("Il y a "+ Counter+ " valeurs nulles");
		return Counter;
	}
	

	
//Methode pour calculer le Pourcentage
	
	public double pourcentageTotal(Map<String, Double> maMap) {
		// TODO Auto-generated method stub
		 double pourcentageTotal;
		 int Counter =Compteur(maMap);//Nombre de valeurs nulles dans l'echantillon
	pourcentageTotal = (Counter * 100)/maMap.size();
	LOG.info("le pourcentage de valeurs nulles dans cet échantillon vaut: "+ pourcentageTotal + " %");
	return pourcentageTotal;
	}
	
	
	
	
//Methode pour créer un nouvelle map à partir d'une map déjà existante	
	
	public Map<String, Double> remplirMapPourcent(Set<Map<String, Double>> monSetDeMaps) {
		// TODO Auto-generated method stub	 		 
		 for(Entry<Set<Map<String, Double>>> monSet: monSetDeMaps) {
			   valeurmaMap2Final = (somme des valeurs pour une key *100)/toutesMesMaps.size());
			   maMap2.put(keymaMap2, valeurmaMap2Final);
		 }
		 
		// LOG.info(" Voici la nouvelle hashmap: "+maMap2.toString());
		 //LOG.info(" Ma nouvelle : "+maMap2.toString());
		 LOG.info("taille de ma collection de map "+toutesMesMaps.size());
			return (maMap2);
		
			
	}
		
		 
	
	
}
