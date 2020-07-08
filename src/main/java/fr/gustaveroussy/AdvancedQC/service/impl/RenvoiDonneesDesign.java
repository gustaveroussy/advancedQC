package fr.gustaveroussy.AdvancedQC.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeaderwD;
import fr.gustaveroussy.AdvancedQC.service.IRenvoiDonneesDesign;

public class RenvoiDonneesDesign implements IRenvoiDonneesDesign {

	@SuppressWarnings("null")
	@Override
	public List<SamplewHeaderwD> renvoyerDonneesDesign(List<String> lignefichierDesign) {
		List<SamplewHeaderwD> listwheaderwd = new ArrayList<>();
		String spliter = "\t";
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
			SamplewHeaderwD samplewhwd = new SamplewHeaderwD(sampleIDdesign, mapDesign, null);
			LOG.debug("samplewheaderwd {}", samplewhwd);
			listwheaderwd.add(samplewhwd);
		
		}
		return listwheaderwd;
	}

	private static Logger LOG = LoggerFactory.getLogger(RenvoiDonneesDesign.class);
}