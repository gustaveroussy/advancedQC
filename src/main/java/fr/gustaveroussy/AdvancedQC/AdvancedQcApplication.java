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
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureMqc;
import fr.gustaveroussy.AdvancedQC.service.IRenvoieDonnesTraitees;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationJSON;
import fr.gustaveroussy.AdvancedQC.service.impl.EcritureMqc;
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
			RempliMapMoy mapMoyExpDesGenes = new RempliMapMoy();
            ICreationJSON creationjson1 = new CreationJSON();
            ICreationJSON creationjson2 =new CreationJSON();
            IEcritureMqc BWforMqc =new EcritureMqc();
            IEcritureMqc BGforMqc =new EcritureMqc();
            
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
			List<SamplewHeader> listwHeader = renvoiMesDonnees.renvoyerDonneesTraitees(lines);
			LOG.info ("listwheader{}",listwHeader);
			
			List<SampleValue> listMeanGeneExpression = mapMoyExpDesGenes.geneExpressionMean(listwHeader);
			LOG.info("moyenne de taux d'expression des genes {}", listMeanGeneExpression);
	

			JSONObject BWjson = creationjson1.createBWJSON(listwHeader);
			BWforMqc.ecritureMqc(BWjson , "/users/hadidjasaid/data/1/BW2_mqc.json");
			JSONObject BGjson = creationjson2.createBGJSON(listwHeader);
			BGforMqc.ecritureMqc(BGjson,"/users/hadidjasaid/data/1/BG2_mqc.json");
			
		
		}else {
			LOG.error("args must be 1");
		}
	}	
}