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
		LOG.info("Hello World :)");
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Bye Bye World");
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//lectureFichier("/Users/hadidjasaid/Documents/small_example_dataset.tsv");
		
		List<String> lines = Files.readAllLines(Paths.get("/Users/hadidjasaid/Documents/small_example_dataset.tsv"), StandardCharsets.UTF_8);  
		lines.remove(0); //elimination du header de la liste "lines"
		Map<String,Double> geneMap1 = new HashMap<String,Double>();
		
	
		
		transformListenTabExploitable(lines, 2);
		pourcentageTotal (geneMap1); //Pourcentage de valeurs nulle sur l'ensemble de l'echantillon		 
		Map<String,Double> genepourcentageMap = new HashMap<String,Double>();//declarer la nouvelle map qui lie gene et pourcentage
	    remplirMapPourcent(genepourcentageMap,geneMap1);//remplit la nouvelle map à partir de geneMap, conservation des mêmes keys et modif des veleurs par calcul				
    	}
		

	
	
	
	
	
	
	
	
	//Méthode pour créer, à partir du fichier .tsv, un tableau de données exploitable
	
public String transformListenTabExploitable(List<String> lignesàtransformer, Integer numechantillon) {
		// TODO Auto-generated method stub
	//String[] toutesmeslignes = null;
	Map<String,Double> maMap = new HashMap<String,Double>();
		for(String str : lignesàtransformer){	
			 String[] toutesmeslignes = str.split("\t");			
				LOG.info("Mes lignes [0]" +toutesmeslignes[0]);// renvoie bien le nom de tout les echantillons
				String valeurStringdufichier = toutesmeslignes [numechantillon]; 
				double valeurDoubledufichier = Double.parseDouble (valeurStringdufichier);	
				maMap.put(toutesmeslignes [0], valeurDoubledufichier);
		}
		LOG.info(maMap.toString());
		return (maMap.toString()) ;
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
	
	
//Methode pour créer un nouvelle map à partir d'un map déjà existante	
	
	private String remplirMapPourcent(Map<String, Double> MaMap2, Map<String, Double> maMap) {
		// TODO Auto-generated method stub
		 double ValeurMaMap2Final;
		 for(Entry<String, Double> e : maMap.entrySet()) {// on utilise les données de MaMap1 pour créer MaMap2
		        String keyMaMap2 = e.getKey();
		        Double valeursMaMap2interm = e.getValue();
		        ValeurMaMap2Final = (valeursMaMap2interm *100/1);// pour la suite: additionner les valeurs des ech + diviser par nbr total d'ech
		    
				MaMap2.put(keyMaMap2, ValeurMaMap2Final);			
		 }
		 LOG.info(" Voici la nouvelle hashmap: "+MaMap2.toString());
		return null;
			
	}
		
}
