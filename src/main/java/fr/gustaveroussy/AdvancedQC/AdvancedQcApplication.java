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

		String[]resultArray ; 
		

		int compteur = 0;
		double resultatCalcul =0;
		double PourcentageTotal;//pourcentage de valeurs nulles pour la totalité des genes
		
		lines.remove(0); //elimination du header
		Map<String,Double> geneMap = new HashMap<String,Double>();
		for(String str : lines){
				resultArray = str.split("\t"); //tab-separated 
    			String valArray = resultArray [1]; //les valeurs sont dans [1] du tableau
      			double valDouble = Double.parseDouble (valArray);	// le tableau est initialement de type string, transformation en type double de façon à pouvoir effectuer des calculs à partir des valeurs qu'il contient		
      			
//				for( int i= 1; i< 2; i++) {
//					valDouble.add(Double.parseDouble(resultArray[i]))  ;
//				}
				geneMap.put(resultArray[0], valDouble);		
		}
		LOG.info(geneMap.toString());
		
		 for (Entry<String, Double> entry : geneMap.entrySet()) {
	            if (entry.getValue().equals(0.0)){
	            	compteur= compteur+1;
	                LOG.info( "Gène: "+ entry.getKey() + ", Valeur: "+ entry.getValue() ); //renvoie les keys corresp aux valeurs nulles
//		for ( String str : lines) {
//			LOG.info(" valeurs nulles ? " + geneMap.containsValue(0.0));//renvoie "true" dès qu'il trouve une val= 0.0
//	                }
	            }  
		 }
		 PourcentageTotal = (compteur * 100)/9; //calcul du pourcentage de valeurs nulles sur la totalité de l'echantillon
		 LOG.info("le pourcentage de valeurs nulles dans l'echantillon 1 vaut: "+ PourcentageTotal + "%");
		
		 Map<String,Double> genepourcentageMap = new HashMap<String,Double>();
		 
		 
		 for(Entry<String, Double> e : geneMap.entrySet()) {// création d'une nouvelle hashmap avec des valeurs modifiées après calcul
		        String keyHM2 = e.getKey();//HM2 = hashmap numéro 2
		        Double valeursHM2 = e.getValue();
		        resultatCalcul = (valeursHM2 *100)/9;
		    
				genepourcentageMap.put(keyHM2, resultatCalcul);			
				}
		 LOG.info(" Voici la nouvelle hashmap");
			LOG.info(genepourcentageMap.toString());
			
	}
}


	







