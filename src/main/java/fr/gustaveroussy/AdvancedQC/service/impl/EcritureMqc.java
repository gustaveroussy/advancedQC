package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.gustaveroussy.AdvancedQC.service.IEcritureMqc;

public class EcritureMqc implements IEcritureMqc{

	public  JsonElement ecritureMqc (JsonElement fichierJSONfinal, String absolutePath) throws IOException {
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter(absolutePath);
				file.write(fichierJSONfinal.toString());
				file.flush();
				return fichierJSONfinal;
				}
//	private static Logger LOG = LoggerFactory.getLogger(EcritureJSON.class);

}
