package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.google.gson.JsonElement;

import fr.gustaveroussy.AdvancedQC.service.IEcritureFiles;

@Service
public class EcritureFiles implements IEcritureFiles {
@Override	
	public JsonElement ecritureMqc(JsonElement fichierJSONfinal, String absolutePath) throws IOException {
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter(absolutePath);
		file.write(fichierJSONfinal.toString());
		file.flush();
		return fichierJSONfinal;
	}
@Override
	public JsonElement ecriturePlotly(JsonElement fichierJSONfinal, String absolutePath) throws IOException {
		@SuppressWarnings("resource")
		FileWriter fileHtml = new FileWriter(absolutePath);
		fileHtml.write("<html>\n" + "<meta charset=\"utf-8\"/>\n" + "<head>\n"
				+ "<script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>\n" + "</head>\n" + "\n"
				+ "<body>)" + "  <div id=\"myChart\"></div>\n" + "\n" + "<script>" + "let myConfig = ");
		fileHtml.write(fichierJSONfinal.toString());
		fileHtml.write(
				";\n" + "TESTER = document.getElementById('myChart');\n" + "Plotly.newPlot( TESTER, myConfig, {\n"
						+ "margin: { t: 0 } } );</script>\n" + "\n" + "\n" + "</body>\n" + "</html>");

LOG.info("fichierJSOnfinall{}",fichierJSONfinal);
LOG.info("absolutepath{}",absolutePath);
		return fichierJSONfinal;

	}
	private static Logger LOG = LoggerFactory.getLogger(EcritureFiles.class);
}
	
	