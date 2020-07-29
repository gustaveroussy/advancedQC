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
	public JsonElement ecritureMqc(JsonElement fichierJSON, String absolutePath) throws IOException {
		@SuppressWarnings("resource")
		FileWriter file = new FileWriter(absolutePath);
		file.write(fichierJSON.toString());
		file.flush();
		return fichierJSON;
	}
@Override
	public JsonElement ecriturePlotly(JsonElement fichierJSON, String absolutePath) throws IOException {
		@SuppressWarnings("resource")
		FileWriter fileHtml = new FileWriter(absolutePath);
		fileHtml.write("<!--\n" + 
				"id: 'custom-html'\n" + 
				"section_name: 'Distribution of expression levels'\n" + 
				"description: 'This section is created using a custom HTML file, with plotly'\n" + 
				"-->\n"+"<!DOCTYPE html>"+"<html>\n".concat("<meta charset=\"utf-8\"/>\n" + "<head>\n"
						+ "<script src=\"https://cdn.plot.ly/plotly-latest.min.js\"></script>\n" + "</head>\n" + "\n"
						+ "<body>"+ "<!-- Chart Placement[2] -->" + "  <div id=\"ChartBoxPlot\"></div>\n" + "\n" + "<script>" + "let configCustom = ").toString());
		fileHtml.write(" ".concat(fichierJSON.toString()));
		fileHtml.write(
				";\n".concat("TESTER = document.getElementById('ChartBoxPlot');\n" + "Plotly.newPlot( TESTER, configCustom, {\n"
						+ "margin: { t: 0 } } );</script>\n" + "\n" + "\n" + "</body>\n" + "</html>").toString());
		fileHtml.flush();
LOG.debug("fichierJSOnfinall{}",fichierJSON);
LOG.debug("absolutepath{}",absolutePath);
		return fichierJSON;

	}
	private static Logger LOG = LoggerFactory.getLogger(EcritureFiles.class);
}
	
	