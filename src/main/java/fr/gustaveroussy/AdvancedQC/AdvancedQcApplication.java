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
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IRenvDatas;
import fr.gustaveroussy.AdvancedQC.service.IRenvDesign;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBGjson;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBPjson;
import fr.gustaveroussy.AdvancedQC.service.impl.CreationBPjson2;
import fr.gustaveroussy.AdvancedQC.service.impl.RenvData2;
import fr.gustaveroussy.AdvancedQC.service.impl.RenvData1;

@SpringBootApplication
public class AdvancedQcApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(AdvancedQcApplication.class);

	@Autowired
	private CreationBGjson creationjson1;
	@Autowired
	private CreationBPjson creationjson2;
	@Autowired
	private CreationBPjson2 creationjson3;
	@Autowired
	private RenvData1 renvoiData1;
	@Autowired
	private RenvData2 renvoiData2;
	@Autowired
	private IRenvDesign renvoiDesign;

	public static void main(String[] args) {
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Done");
	}

	@Override
	public void run(String... args) throws Exception {
		// Add here new ICreationJSON class service for new format export
		ArrayList<ICreationJSON> creationjsonArray = new ArrayList<ICreationJSON>();
		IRenvDatas renvoiData = renvoiData2;

		// si RNAseqCount
		if (args.length < 3|| args.length>4) {
			LOG.error(
					"usage: java -jar <absolute_path>/advancedQC-<version>.jar keyword <absolute_path>/file.tsv <local directory> <absolute_path>/optional-design.tsv");

		}else if (args[0].equals("RNAseqCount")) {
			creationjsonArray.add(creationjson1);
			creationjsonArray.add(creationjson2);
			renvoiData = renvoiData1;
		}
		// sinon si DeepTools
		 else if (args[0].equals("DeepTools")) {
			creationjsonArray.add(creationjson3);
			renvoiData = renvoiData2;
		}
		 else {
			throw new IllegalArgumentException("incorrect keyword,enter 'RNAseqCount'" + "or" + "'DeepTools'.");			
		}	

		
		if (args.length == 3) {
			File localDirectoryjson = new File(args[2]);
			File localDirectoryData = new File(args[1]);
			if (localDirectoryData.isFile() & localDirectoryjson.isDirectory()) {
				List<String> lineData = Files.readAllLines(localDirectoryData.toPath(), StandardCharsets.UTF_8);
				List<SamplewHeader> listwHeader = renvoiData.renvoyerDonneesTraitees(lineData);
				LOG.debug("listwheader{}", listwHeader);
				for (ICreationJSON creationprime : creationjsonArray) {
					creationprime.export(localDirectoryjson.getAbsolutePath(), listwHeader);
				}
			} else {
				throw new IllegalArgumentException("args incorrect :" + localDirectoryData + " is a not a file" + " or "
						+ localDirectoryjson + " is a not a directory.");
			}
		} else if (args.length == 4) {
			File localDirectoryjson = new File(args[2]);
			File localDirectoryData = new File(args[1]);
			File localDirectoryDesign = new File(args[3]);
			if (localDirectoryData.isFile() & localDirectoryjson.isDirectory() & localDirectoryDesign.isFile()) {

				List<String> lineData = Files.readAllLines(localDirectoryData.toPath(), StandardCharsets.UTF_8);
				List<SamplewHeader> listwHeader = renvoiData1.renvoyerDonneesTraitees(lineData);
				LOG.debug("listwheader{}", listwHeader);

				// ajout du fichier design
				List<String> linewdesign = Files.readAllLines(localDirectoryDesign.toPath(), StandardCharsets.UTF_8);
				List<SamplewHeaderwD> listwHeaderwD = renvoiDesign.renvoyerDonneesDesign(linewdesign, listwHeader);
				LOG.debug("listwheaderwd {}", listwHeaderwD);

				for (ICreationJSON creationprime : creationjsonArray) {
					creationprime.export(localDirectoryjson.getAbsolutePath(), listwHeaderwD);
				}
			} else {
				throw new IllegalArgumentException(
						"args incorrect :" + localDirectoryData + " is a not a file" + " or " + localDirectoryjson
								+ " is a not a directory" + " or " + localDirectoryDesign + " is not a file. ");
			}
		} 
	}
}
