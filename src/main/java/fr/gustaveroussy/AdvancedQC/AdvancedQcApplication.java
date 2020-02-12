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

import fr.gustaveroussy.AdvancedQC.header.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SampleValue;






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
			IRenvoieDonnesTraitees renvoiMesDonnees = new renvoieDonneesTraitees();
			PourcentageTotal pourcentageDeValNull = new PourcentageTotal();
			RempliMapMoy mapMoyExpDesGenes =new RempliMapMoy();
	
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);  
			List<SamplewHeader> listwHeader=renvoiMesDonnees.renvoyerDonneesTraitees(lines);			
			List<SampleValue>listPercentValNull = pourcentageDeValNull.pourcenTotale(listwHeader);
			LOG.info("pourcentage de valeurs nulles: " + listPercentValNull);
			List<SampleValue> listMeanGeneExpression = mapMoyExpDesGenes.geneExpressionMean(listwHeader);
			LOG.info("moyenne de taux d'expression des genes" + listMeanGeneExpression);
		
		
		
		}else {
			LOG.error("args must be 1");
		}
	}	
}
	

