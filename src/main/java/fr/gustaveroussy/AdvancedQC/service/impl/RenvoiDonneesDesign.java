package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.IRenvoiDonneesDesign;

@Service
public class RenvoiDonneesDesign implements IRenvoiDonneesDesign {

	@SuppressWarnings("null")
	@Override
	public List<SamplewHeaderwD> renvoyerDonneesDesign(List<String> lignefichierDesign, List<SamplewHeader> listwh) {

		String spliter = "\t";
		List<SamplewHeaderwD> listwhwD = new ArrayList<>();
		String[] colonnewheaderwd = lignefichierDesign.get(0).split(spliter);
		lignefichierDesign.remove(0);
		
		for (String strdesign : lignefichierDesign) {
			String[] colonnedesign = strdesign.split(spliter);
			Map<String, String> mapDesign = new HashMap<String, String>();
			for (int i = 3; i < colonnedesign.length; i++) {
				mapDesign.put(colonnewheaderwd[i], colonnedesign[i]);
			}
			LOG.debug("mapdesign {}", mapDesign);
			
			String sampleIDdesign = colonnedesign[2];
			
			Map<String, Double>	mapGeneValDsg = getGeneVal(listwh,sampleIDdesign);
			if(mapGeneValDsg == null) {
				LOG.error("missing {}",sampleIDdesign);
			}
			
			SamplewHeaderwD samplewhwd = new SamplewHeaderwD(sampleIDdesign, mapDesign,mapGeneValDsg);
			LOG.debug("mapGenValDsg {}",mapGeneValDsg);
			LOG.debug("samplewheaderwd {}", samplewhwd);
			listwhwD.add(samplewhwd);
				
		}
		return listwhwD;
	}

	private Map<String, Double> getGeneVal(List<SamplewHeader> listsamplewh, String samplewhwd) {
		for (int i = 0; i < listsamplewh.size(); i++) {
			if (listsamplewh.get(i).getSampleID().equalsIgnoreCase(samplewhwd))
				return listsamplewh.get(i). getSampleGeneVal();
		}
		return null;
	}
	private static Logger LOG = LoggerFactory.getLogger(RenvoiDonneesDesign.class);
}