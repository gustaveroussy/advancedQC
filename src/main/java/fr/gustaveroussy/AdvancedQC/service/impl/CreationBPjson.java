package fr.gustaveroussy.AdvancedQC.service.impl;

import java.io.File;
import java.io.IOException;

import java.util.Collection;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.ICreationJSON;
import fr.gustaveroussy.AdvancedQC.service.IEcritureFiles;

@Component("CreationBPjson1")
//@Primary //Field creationjson2 required a single bean, but 2 were found
@Service
public class CreationBPjson implements ICreationJSON {
	@Autowired
	IEcritureFiles ecriturePlotly;

	@Override
	public JsonElement createJSON (List<? extends SamplewHeader> listwHeader ) {
		JsonArray bpJSON = new JsonArray();		
		
		for (SamplewHeader samplh : listwHeader) {			 
			Collection<Double> valsampleinter1 = samplh.getSampleGeneVal().values();
			Double[] valsampleinter2 = valsampleinter1.toArray(new Double[valsampleinter1.size()]);
			double[] valsamplefinal = ArrayUtils.toPrimitive(valsampleinter2);
			double decilmin = StatUtils.percentile(valsamplefinal, 10);
			double decilmax = StatUtils.percentile(valsamplefinal, 90);
			double Q1 = StatUtils.percentile(valsamplefinal, 25);
			double Q3 = StatUtils.percentile(valsamplefinal, 75);
			double med = StatUtils.percentile(valsamplefinal, 50);			


			JsonObject property = new JsonObject();
				property.addProperty("type","box");
				property.addProperty("name",samplh.getSampleID());
				property.addProperty("boxpoints",false);
				
				//ajout design
//				if(samplh instanceof SamplewHeaderwD) {
//					SamplewHeaderwD splwhD = (SamplewHeaderwD)samplh;
//					property.addProperty("name", splwhD.getSampleCondition().toString());
//					JsonObject color= new JsonObject();
//					JsonObject marker= new JsonObject();
//					color.addProperty("color", "rgb(0,128,128)");choix de couleur nok
//					marker.add("marker", color);
//					bpJSON.add(color);
//		}
					
				
				JsonArray d1 = new JsonArray();
				JsonArray d9 = new JsonArray();
				JsonArray q1 = new JsonArray();
				JsonArray q3 = new JsonArray();
				JsonArray medi = new JsonArray();


				d1.add(decilmin);
				d9.add(decilmax);
				q1.add(Q1);
				q3.add(Q3);
				medi.add(med);
				
				property.add("lowerfence", d1);					
				property.add("q1", q1);
				property.add("median", medi);
				property.add("q3", q3);
				property.add("upperfence", d9);
				
				

				bpJSON.add(property);	
			
				LOG.debug("bpjson{},", bpJSON);
			}
			return bpJSON;
		}
		
	@Override
	public void export(String filePath, List<? extends SamplewHeader> listwHeader) throws IOException {
		JsonElement fileplotly = this.createJSON(listwHeader);
		ecriturePlotly.ecriturePlotly(fileplotly, filePath + File.separator+ this.getClass().getSimpleName().concat("_mqc.html"));
		LOG.debug("fileplotly{}", fileplotly);

	}

	private static Logger LOG = LoggerFactory.getLogger(CreationBPjson.class);

}


		


