package fr.gustaveroussy.AdvancedQC.service;

import java.util.List;
import fr.gustaveroussy.AdvancedQC.model.SamplewHeader;

public interface IRenvoieDonnesTraitees {
	List<SamplewHeader> renvoyerDonneesTraitees(List<String> lignesdefichier);
}