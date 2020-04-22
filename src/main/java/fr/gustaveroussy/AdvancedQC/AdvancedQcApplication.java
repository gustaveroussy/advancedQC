package fr.gustaveroussy.AdvancedQC;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files ;
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
		
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Done");		
	}
	@Override
	public void run(String... args) throws Exception {
	
		if (args.length == 2) {
			
			IRenvoieDonnesTraitees renvoiMesDonnees = new RenvoieDonneesTraitees();
            ICreationJSON creationjson1 = new CreationBWjson();
            ICreationJSON creationjson2 =new CreationBGjson();
            ArrayList<ICreationJSON> creationjsonArray= new ArrayList<ICreationJSON>();
            creationjsonArray.add(creationjson1);
            creationjsonArray.add(creationjson2);
            IEcritureMqc jsonForMqc =new EcritureMqc();
  
            File localDirectory = new File(args[0]);
			if(! localDirectory.isFile()){
				throw new IllegalArgumentException("'"+args[0] +"' is a not a file");
			}
			List<String> lines = Files.readAllLines(localDirectory.toPath(), StandardCharsets.UTF_8);
			List<SamplewHeader> listwHeader = renvoiMesDonnees.renvoyerDonneesTraitees(lines);
			LOG.debug ("listwheader{}",listwHeader);
	
			for (ICreationJSON creationprime : creationjsonArray) {
            	JSONObject filemqc = creationprime.createJSON(listwHeader);
            	jsonForMqc.ecritureMqc (filemqc,args[1]+ creationprime.getClass().getSimpleName().concat("2_mqc.json"));
            	LOG.debug("filemqc{}",filemqc);
				}

		}else {
			if (args.length !=2){
				LOG.error("args must be 2");
			}		
			
	}	
  }
}