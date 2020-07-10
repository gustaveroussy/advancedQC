package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.IRenvoiDonneesDesign;

public class RenvoiDonneesDesign implements IRenvoiDonneesDesign {

	@SuppressWarnings("null")
	@Override
	public List<SamplewHeaderwD> renvoyerDonneesDesign(List<String> lignefichierDesign, List<SamplewHeader> listwh) {

		String spliter = "\t";
		List<SamplewHeaderwD> listwhwD = new ArrayList<>();
		String[] colonnewheaderwd = lignefichierDesign.get(0).split(spliter);
//		List<SamplewHeader> listwh = new ArrayList<>();
//		String[] colonwH = lignefichierData.get(0).split(spliter);
//		lignefichierData.remove(0);
//		for (int i = 1; i < colonwH.length; i++) {
//			Map<String, Double> mapGeneVal = changeListenTabExploitable(lignefichierData, i, spliter);
//			SamplewHeader sampleheader = new SamplewHeader(colonwH[i], mapGeneVal);
//			listwh.add(sampleheader);
//			LOG.debug("mapgeneval {}", mapGeneVal);
//			LOG.debug("sampleId {}",sampleheader.getSampleID());
//		}
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
				//TODO throw exception
				LOG.error("missing {}",sampleIDdesign);
			}
			
			SamplewHeaderwD samplewhwd = new SamplewHeaderwD(sampleIDdesign, mapDesign,mapGeneValDsg);
			LOG.info("mapGenValDsg {}",mapGeneValDsg);
			LOG.info("samplewheaderwd {}", samplewhwd);
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

	private Map<String, Double> changeListenTabExploitable(List<String> lignesatransformer, Integer numechantillon,
			String spliter) throws IllegalArgumentException {
		Map<String, Double> maMap = new HashMap<String, Double>();
		for (String str : lignesatransformer) {
			String[] toutesmescolonnes = str.split(spliter);
			if (numechantillon + 1 > toutesmescolonnes.length) {
				throw new IllegalArgumentException("anomaly detected in the number of samples,please check your file");
			} else {
				String valeurStringdufichier = toutesmescolonnes[numechantillon];
				double valeurDoubledufichier = Double.parseDouble(valeurStringdufichier);
				maMap.put(toutesmescolonnes[0], valeurDoubledufichier);
			}
		}
		LOG.debug("map exploitable {}", maMap);

		return (maMap);
	}

	private static Logger LOG = LoggerFactory.getLogger(RenvoiDonneesDesign.class);
}