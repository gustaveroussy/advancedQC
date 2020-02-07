package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
			renvoieDonneesTraitees renvoiMesDonnees = new renvoieDonneesTraitees();
			PourcentageTotal monpourcent = new PourcentageTotal();
			rempliMapPourcent mapAssocPourcentExp =new rempliMapPourcent();
	
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);  
			List<Map<String, Double>> toutesMesMaps=renvoiMesDonnees.renvoyerDonneesTraitees(lines);			
			monpourcent.pourcenTotale(toutesMesMaps);
			mapAssocPourcentExp.geneExpressionMean(toutesMesMaps);
		
		
		
		}else {
			LOG.error("args must be 1");
		}
	}	
}
	

