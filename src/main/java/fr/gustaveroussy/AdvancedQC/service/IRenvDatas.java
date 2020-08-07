package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface IRenvDatas {
	List<SamplewHeader> renvoyerDonneesTraitees(List<String> lignesdefichier);
}