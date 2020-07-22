package fr.gustaveroussy.AdvancedQC;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.gson.JsonElement;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureMqc;
import fr.gustaveroussy.AdvancedQC.service.IRenvoiDonneesDesign;
import fr.gustaveroussy.AdvancedQC.service.IRenvoiDonnesTraitees;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBGjson;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBWjson;

@SpringBootApplication
public class AdvancedQcApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(AdvancedQcApplication.class);

	@Autowired
	private CreationBWjson creationjson1;
	@Autowired
	private CreationBGjson creationjson2;
	@Autowired
	private IEcritureMqc jsonForMqc;
	@Autowired
	private IRenvoiDonnesTraitees renvoiMesDonnees;
	@Autowired
	private IRenvoiDonneesDesign renvoiDesign;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Done");
	}

	@Override
	public void run(String... args) throws Exception {
		//Add here new ICreationJSON class service for new format export
		ArrayList<ICreationJSON> creationjsonArray = new ArrayList<ICreationJSON>();
		creationjsonArray.add(creationjson1);
		creationjsonArray.add(creationjson2);

		if (args.length == 2) {
			File localDirectoryData = new File(args[0]);
			File localDirectoryjson = new File(args[1]);
			if (localDirectoryData.isFile() & localDirectoryjson.isDirectory()) {
				List<String> lineData = Files.readAllLines(localDirectoryData.toPath(), StandardCharsets.UTF_8);
				List<SamplewHeader> listwHeader = renvoiMesDonnees.renvoyerDonneesTraitees(lineData);
				LOG.debug("listwheader{}", listwHeader);

				for (ICreationJSON creationprime : creationjsonArray) {
					JsonElement filemqc = creationprime.createJSON(listwHeader);
					jsonForMqc.ecritureMqc(filemqc,
							args[1] + creationprime.getClass().getSimpleName().concat("_mqc.json"));
					LOG.debug("filemqc{}", filemqc);
				}
			}else {
				throw new IllegalArgumentException("args incorrect :" + localDirectoryData + " is a not a file" + " or " + localDirectoryjson
						+ " is a not a directory.");

			}
		} else if (args.length == 3) {
			File localDirectoryData = new File(args[0]);
			File localDirectoryjson = new File(args[1]);
			File localDirectoryDesign = new File(args[2]);

			if (localDirectoryData.isFile() & localDirectoryjson.isDirectory() & localDirectoryDesign.isFile()) {

				List<String> lineData = Files.readAllLines(localDirectoryData.toPath(), StandardCharsets.UTF_8);
				List<SamplewHeader> listwHeader = renvoiMesDonnees.renvoyerDonneesTraitees(lineData);
				LOG.debug("listwheader{}", listwHeader);

				// ajout du fichier design
				List<String> linewdesign = Files.readAllLines(localDirectoryDesign.toPath(), StandardCharsets.UTF_8);
				List<SamplewHeaderwD> listwHeaderwD = renvoiDesign.renvoyerDonneesDesign(linewdesign, listwHeader);
				LOG.debug("listwheaderwd {}", listwHeaderwD);

				for (ICreationJSON creationprime : creationjsonArray) {
					JsonElement filemqc = creationprime.createJSON(listwHeaderwD);
					jsonForMqc.ecritureMqc(filemqc,
							args[1] + creationprime.getClass().getSimpleName().concat("_mqc.json"));
					LOG.debug("filemqc{}", filemqc);
				}
			}else {
				throw new IllegalArgumentException("args incorrect :" + localDirectoryData + " is a not a file" + " or " + localDirectoryjson
						+ " is a not a directory" + " or " + localDirectoryDesign + " is not a file. ");

			}
		} else {
			LOG.error("usage: java -jar <absolute_path>/advancedQC-<version>.jar <absolute_path>/file.tsv <local directory> <absolute_path>/optional-design.tsv");
		}


	}
}
