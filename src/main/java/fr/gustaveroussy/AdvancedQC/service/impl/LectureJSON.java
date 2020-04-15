package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import fr.gustaveroussy.AdvancedQC.service.ILectureJSON;

public class LectureJSON implements ILectureJSON{

	public  JSONObject lectureJSON (JSONObject fichierJSONfinal, String pathAbsolute) throws IOException {
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter(pathAbsolute);
				file.write(fichierJSONfinal.toJSONString());
				file.flush();
				return fichierJSONfinal;
				}
//	private static Logger LOG = LoggerFactory.getLogger(LectureJSON.class);

}
