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
		

		int k = 0;
		double n;
		double Pourcentage;//pourcentage de valeurs nulles pour la totalité des genes
		double P= 0.0;
		
		lines.remove(0);
		Map<String,Double> geneMap = new HashMap<String,Double>();
		for(String str : lines){
				resultArray = str.split("\t"); //tab-separated 
    			String valArray = resultArray [1];
      			double valDouble = Double.parseDouble (valArray);
      			
      			
//				for( int i= 1; i< 2; i++) {
//					valDouble.add(Double.parseDouble(resultArray[i]))  ;
//				}
				geneMap.put(resultArray[0], valDouble);		
		}
		LOG.info(geneMap.toString());
		
		 for (Entry<String, Double> entry : geneMap.entrySet()) {
	            if (entry.getValue().equals(0.0)){
	            	k= k+1;
	                LOG.info( "Gène: "+ entry.getKey() + ", Valeur: "+ entry.getValue() ); //renvoie les keys corresp aux valeurs nulles
//		for ( String str : lines) {
//			LOG.info(" valeurs nulles ? " + geneMap.containsValue(0.0));//renvoie "true" dès qu'il trouve une val= 0.0
//	                }
	            }  
		 }
		 Pourcentage = (k * 100)/9; //calcul du pourcentage de valeur nulles sur la totalité de l'echantillon
		 LOG.info("le pourcentage de valeurs nulles vaut: "+ Pourcentage);
		 
		 
				for( Double glu : geneMap.values()) { //itération sur les valeurs de la hashmap et permet de faire calcul pour chaque gene
			     n= (glu *100)/9;
			    LOG.info("Resultat du calcul  = " + n);
			}
	}
	}
	







