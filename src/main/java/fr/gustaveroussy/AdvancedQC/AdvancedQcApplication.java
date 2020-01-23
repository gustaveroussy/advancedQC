package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.io.IOException;
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
		
		List<String> lines = Files.readAllLines(Paths.get("/Users/hadidjasaid/Documents/small_example_dataset.tsv"), StandardCharsets.UTF_8);  
	
		
	
		renvoieDonneesTraitees(lines);
		
//		Map<String,Double> geneMap1=transformListenTabExploitable(lines, 1);
////		Compteur test = new Compteur(); //
////		test.Counter(geneMap1);
//		pourcentageTotal (geneMap1); //Pourcentage de valeurs nulle sur l'ensemble de l'echantillon		 
//	    remplirMapPourcent(geneMap1);//remplit la nouvelle map à partir de geneMap, conservation des mêmes keys et modif des veleurs par calcul				
//    	}
		
		
	}
	
	
public Map <String, Double> renvoieDonneesTraitees (List<String> lignesdefichier){
		 // TODO Auto-generated method stub
	Map<String,Double> maMap = null;
	lignesdefichier.remove(0); //elimination du header de la liste "lines"
		 String[] toutesmescolonnes = lignesdefichier.get(0).split("\t");	
			for (int i = 1; i<toutesmescolonnes.length; i++) {
				 maMap=transformListenTabExploitable(lignesdefichier, i);	
				pourcentageTotal (maMap); 
				LOG.info("Ma Map pour l'echantillon "+ i + ": "+maMap.toString());
			    remplirMapPourcent(maMap);
		  
			}
			return (maMap);
	}
		
	
	//Méthode pour créer, à partir du fichier .tsv, un tableau de données exploitable
	
public Map<String, Double> transformListenTabExploitable(List<String> lignesatransformer, Integer numechantillon) {
		// TODO Auto-generated method stub
	Map<String,Double> maMap = new HashMap<String,Double>();
		for(String str : lignesatransformer){	
			 String[] toutesmescolonnes = str.split("\t");			
//				LOG.info("Mes lignes [0]" +toutesmeslignes[0]);// renvoie bien le nom de tout les echantillons
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
	
	private Map<String, Double> remplirMapPourcent(Map<String, Double> maMap) {
		// TODO Auto-generated method stub
		 double valeurmaMap2Final;
		 Map<String, Double> maMap2= new HashMap<String,Double>();
		 for(Entry<String, Double> e : maMap.entrySet()) {// on utilise les données de maMap pour créer maMap2
		        String keymaMap2 = e.getKey();
		        Double valeursmaMap2interm = e.getValue();
		        valeurmaMap2Final = (valeursmaMap2interm *100/1);// pour la suite: additionner les valeurs des ech + diviser par nbr total d'ech
		    
				maMap2.put(keymaMap2, valeurmaMap2Final);			
		 }
		 LOG.info(" Voici la nouvelle hashmap: "+maMap2.toString());
		return (maMap2);
			
	}
		
}
