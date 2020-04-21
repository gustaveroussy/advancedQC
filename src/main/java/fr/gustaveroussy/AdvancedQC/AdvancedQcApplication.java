package fr.gustaveroussy.AdvancedQC;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files ;
import java.nio.file.Path;
import fr.gustaveroussy.AdvancedQC.model.SampleValue;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureMqc;
import fr.gustaveroussy.AdvancedQC.service.IRenvoieDonnesTraitees;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBGjson;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBWjson;
import fr.gustaveroussy.AdvancedQC.service.impl.EcritureMqc;
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
		if (args.length == 2) {
			IRenvoieDonnesTraitees renvoiMesDonnees = new RenvoieDonneesTraitees();
            ICreationJSON creationjson1 = new CreationBWjson();
            ICreationJSON creationjson2 =new CreationBGjson();
            ArrayList<ICreationJSON> creationjsonArray= new ArrayList<ICreationJSON>();
            creationjsonArray.add(creationjson1);
            creationjsonArray.add(creationjson2);
            //String path1 = "/Users/hadidjasaid/data/1";
            Path path1 = Paths.get(args[1]);
            IEcritureMqc jsonForMqc =new EcritureMqc();
  
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
			List<SamplewHeader> listwHeader = renvoiMesDonnees.renvoyerDonneesTraitees(lines);
			LOG.info ("listwheader{}",listwHeader);
	
			for (ICreationJSON creationprime : creationjsonArray) {
            	JSONObject filemqc = creationprime.createJSON(listwHeader);
            	jsonForMqc.ecritureMqc (filemqc,path1+ creationprime.getClass().getName().concat("_mqc.json"));
            	LOG.info("filemqc{}",filemqc);
				}

		}else {
			LOG.error("args must be 2");
		}
	}	
}