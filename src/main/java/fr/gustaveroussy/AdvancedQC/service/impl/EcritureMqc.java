package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.FileWriter;
import java.io.IOException;


import org.json.simple.JSONObject;
import fr.gustaveroussy.AdvancedQC.service.IEcritureMqc;

public class EcritureMqc implements IEcritureMqc{

	public  JSONObject ecritureMqc (JSONObject fichierJSONfinal, String absolutePath) throws IOException {
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter(absolutePath);
				file.write(fichierJSONfinal.toJSONString());
				file.flush();
				return fichierJSONfinal;
				}
//	private static Logger LOG = LoggerFactory.getLogger(EcritureJSON.class);

}
