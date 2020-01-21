package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
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
		LOG.info("Hello World huhu");
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Bye Bye");
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		List<String> lines = Files.readAllLines(Paths.get("/Users/hadidjasaid/Documents/small_example_dataset.tsv"), StandardCharsets.UTF_8); 

		String[]resultArray = null ; 
		lines.remove(0); //elimination du header de la liste "lines"
		
		
		Map<String,Double> geneMap1 = new HashMap<String,Double>();
		Tab_Donnees(resultArray, geneMap1, 1, lines);
//		for(String str : lines){
//				resultArray = str.split("\t"); //tab-separated 
//    			String valArray = resultArray [1]; //les valeurs de l'ech 1 sont dans la case[1] du tableau
//      			double valDouble = Double.parseDouble (valArray);	// le tableau est initialement de type string, transformation en type double de façon à pouvoir effectuer des calculs		
//      			
//				geneMap1.put(resultArray[0], valDouble);		
//		}
//		LOG.info(geneMap1.toString());
		
		Compteur(geneMap1);//Nombre de valeurs nulles dans l'echantillon		
		PourcentageTotal (geneMap1); //Pourcentage de valeurs nulle sur l'ensemble de l'echantillon
		 
		Map<String,Double> genepourcentageMap = new HashMap<String,Double>();//declarer la nouvelle map
	    RemplirMapPourcent(genepourcentageMap,geneMap1);//remplit la nouvelle map à partir de geneMap, conservation des mêmes keys et modif des veleurs par calcul				
	}

	

 List <String> lines ; //= Files.readAllLines(Paths.get("/Users/hadidjasaid/Documents/small_example_dataset.tsv"), StandardCharsets.UTF_8); 	
 public void Tab_Donnees(String [] tab, Map<String, Double> MaMap1, Integer i, List L) throws IOException {	
	// TODO Auto-generated method stub
	
	for(String str : lines){
		tab = str.split("\t"); //tab-separated 
		String tab_i = tab [i]; //les valeurs de l'ech 1 sont dans la case[1] du tableau
			double valDouble = Double.parseDouble (tab_i);	// le tableau est initialement de type string, transformation en type double de façon à pouvoir effectuer des calculs		
			
		MaMap1.put(tab [0], valDouble);		
}
LOG.info(MaMap1.toString());
	}




//Methode pour compter les valeurs nulles par échantillon
	public int Counter;

	private void Compteur (Map <String,Double> MaMap) {
		// TODO Auto-generated method stub
		for (Entry <String, Double> entry: MaMap.entrySet()) {
			if(entry.getValue().equals(0.0)) {
				Counter = Counter+1 ;
			}
		}
		LOG.info ("Il y a "+ Counter+ " valeurs nulles");
	}
	
//Methode pour calculer le Pourcentage
	public double PourcentageTotal;
	private void PourcentageTotal(Map<String, Double> MaMap) {
		// TODO Auto-generated method stub
	PourcentageTotal = (Counter * 100)/MaMap.size();
	LOG.info("le pourcentage de valeurs nulles dans cet échantillon vaut: "+ PourcentageTotal + " %");
	}
	
	
//Methode pour créé un nouvelle map à partir d'un map deja existante	
	public double ValeurMaMap2Final;
	private void RemplirMapPourcent(Map<String, Double> MaMap2, Map<String, Double> MaMap1) {
		// TODO Auto-generated method stub
		 for(Entry<String, Double> e : MaMap1.entrySet()) {// on utilise les données de MaMap1 pour créer MaMap2
		        String keyMaMap2 = e.getKey();
		        Double valeursMaMap2interm = e.getValue();
		        ValeurMaMap2Final = (valeursMaMap2interm *100/1);// pour la suite: additionner les valeurs des ech + diviser par nbr total d'ech
		    
				MaMap2.put(keyMaMap2, ValeurMaMap2Final);			
		 }
		 LOG.info(" Voici la nouvelle hashmap: "+MaMap2.toString());
			
	}
		
}
