package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.IRenvData2;

@Service
public class RenvData2 implements IRenvData2{
	@Override
	public List<SamplewHeader> renvData2(List<String> lignefichierData) {
		List<String> linefiles = new ArrayList<String>(lignefichierData);
		
        String spliter = "\t";
		String[] nbcolum = linefiles.get(0).split(spliter);
		
		linefiles.remove(0);
		List<SamplewHeader> listwHeader = new ArrayList<>();
		for (String strline : linefiles) {
			
			String[] nblines = strline.split(spliter);
			Map < String, Double>mapData = new HashMap<String,Double>();
			String sampleID = nblines[0];
			LOG.info("sampleID {} ",sampleID);
			
			for(int i = 1; i<nblines.length; i++) {
				String realvalpercentil= nblines[i];
				LOG.debug("realvalpercentil {}",realvalpercentil);
				String namepercentil= nbcolum[i];
				LOG.debug("namepercentil {}", namepercentil);
				double valpercentildouble = Double.parseDouble(realvalpercentil);
				mapData.put(namepercentil, valpercentildouble);
				LOG.info("mapData {} ", mapData);			
			}
			
			
			SamplewHeader samplwh = new SamplewHeader (sampleID,mapData);
					
			LOG.info("samplwh {} ",samplwh);
			listwHeader.add(samplwh);
			LOG.info("listwHeader {}",listwHeader);
		}
		return listwHeader;
		}
	
			
		
	

	private static Logger LOG = LoggerFactory.getLogger(CreationBPjsonDirect.class);
}



