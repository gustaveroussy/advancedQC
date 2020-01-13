package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		

//		for(int i =0; i<lines.size(); i++){ 
//			resultArray[i] = lines.get(i).split("\t"); //tab-separated 
//					
//	}
		lines.remove(0);
		Map<String,List<Double>> geneMap = new HashMap<String,List<Double>>();
		for(String str : lines){
				resultArray = str.split("\t"); //tab-separated 
				List <Double> valDouble = new ArrayList<Double>() ;
				for( int i= 1; i< resultArray.length; i++) {
					valDouble.add(Double.parseDouble(resultArray[i]))  ;
				}
				geneMap.put(resultArray[0], valDouble);
				
		}
		LOG.info(geneMap.toString());
		
//		LOG.info("création du tableau à partir des données du fichier.tsv effectuée");
//		LOG.info( Arrays.deepToString( resultArray ) );// affichage du tableau
//		
//		LOG.info( "lancement conversion du tableau String en Double");
//		
//		double[][] resultDouble = new double [resultArray.length][resultArray[0].length]; //conversion du tableau String en Double
//		for(int i=1; i<resultArray.length; i++) {
//			LOG.info(Arrays.deepToString (resultArray [i]));
//		    for(int j=1; j<resultArray[i].length; j++) {
//		    	LOG.info (resultArray [i][j]);
//		        resultDouble[i][j]= Double.parseDouble(resultArray[i][j]);
//		    }
//		}
	
		
		
		
		
		
		
	}
}



