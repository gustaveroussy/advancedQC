package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;

import java.util.List;


import java.nio.charset.StandardCharsets;
import java.nio.file.Files ;


import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationBargraphJSON;
import fr.gustaveroussy.AdvancedQC.service.ICreationBeeswarmJSON;
import fr.gustaveroussy.AdvancedQC.service.IDistributionNivExpression;
import fr.gustaveroussy.AdvancedQC.service.IRenvoieDonnesTraitees;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBargraphJSON;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBeeswarmJSON;
import fr.gustaveroussy.AdvancedQC.service.impl.DistributionDesNivExpression;

import fr.gustaveroussy.AdvancedQC.service.impl.PourcentageTotal;
import fr.gustaveroussy.AdvancedQC.service.impl.RempliMapMoy;
import fr.gustaveroussy.AdvancedQC.service.impl.RenvoieDonneesTraitees;






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
		if (args.length == 1) {
			IRenvoieDonnesTraitees renvoiMesDonnees = new RenvoieDonneesTraitees();
			PourcentageTotal pourcentageDeValNull = new PourcentageTotal();
			RempliMapMoy mapMoyExpDesGenes = new RempliMapMoy();
			IDistributionNivExpression distrNivExpr = new DistributionDesNivExpression();
            ICreationBeeswarmJSON creationbeeswarm = new CreationBeeswarmJSON();
            ICreationBargraphJSON creationbargraph =new CreationBargraphJSON();
            
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
			List<SamplewHeader> listwHeader = renvoiMesDonnees.renvoyerDonneesTraitees(lines);
			LOG.info ("listwheader{}",listwHeader);
			JSONObject listPercentValNull = pourcentageDeValNull.pourcenTotale(listwHeader);
			LOG.info("pourcentage de valeurs nulles: {} " , listPercentValNull);
			List<SampleValue> listMeanGeneExpression = mapMoyExpDesGenes.geneExpressionMean(listwHeader);
			LOG.info("moyenne de taux d'expression des genes {}", listMeanGeneExpression);
		
			JSONObject decilemin = distrNivExpr.calculDecileMin(listwHeader);
			LOG.info("D1 {}", decilemin);
			JSONObject  decilemax = distrNivExpr.calculDecileMax(listwHeader);
			LOG.info("D9 {}", decilemax);
			JSONObject  quartileQ1= distrNivExpr.calculQ1(listwHeader);
			LOG.info("Q1 {}", quartileQ1);
			JSONObject  quartileQ3= distrNivExpr.calculQ3(listwHeader);
			LOG.info("Q3 {}", quartileQ3);
			JSONObject  mediane = distrNivExpr.calculMediane(listwHeader);
			LOG.info("mediane {}", mediane);
			
			creationbeeswarm.createBWJSON(decilemin, decilemax, quartileQ1, mediane, quartileQ3);
			creationbargraph.createBrGJSON(listPercentValNull);
			
			
		
		}else {
			LOG.error("args must be 1");
		}
	}	
}
	

