package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.service.IRenvDatas;

@Service
public class RenvData2 implements IRenvDatas{
	@Override
	public List<SamplewHeader> renvoyerDonneesTraitees(List<String> lignefichierData) {
		List<String> linefiles = new ArrayList<String>(lignefichierData);
		
        String spliter = "\t";
		String[] nbcolum = linefiles.get(0).split(spliter);
		
		linefiles.remove(0);
		List<SamplewHeader> listwHeader = new ArrayList<>();
		for (String strline : linefiles) {
			
			String[] nblines = strline.split(spliter);
			Map < String, Double>mapData = new HashMap<String,Double>();
			String sampleID = nblines[0];
			LOG.debug("sampleID {} ",sampleID);
			
			for(int i = 1; i<nblines.length; i++) {
				String valpercentil= nblines[i];
				LOG.debug("realvalpercentil {}",valpercentil);
				String namepercentil= nbcolum[i];
				LOG.debug("namepercentil {}", namepercentil);
				double valpercentildouble = Double.parseDouble(valpercentil);
				mapData.put(namepercentil, valpercentildouble);
				LOG.debug("mapData {} ", mapData);			
			}	
			SamplewHeader samplwh = new SamplewHeader (sampleID,mapData);		
			LOG.debug("samplwh {} ",samplwh);
			listwHeader.add(samplwh);
			LOG.debug("listwHeader {}",listwHeader);
		}
		return listwHeader;
		}
	
			
		
	

	private static Logger LOG = LoggerFactory.getLogger(CreationBPjson2.class);
}



