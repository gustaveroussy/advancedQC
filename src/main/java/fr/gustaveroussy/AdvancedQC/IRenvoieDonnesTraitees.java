package fr.gustaveroussy.AdvancedQC;

import java.util.List;

import fr.gustaveroussy.AdvancedQC.header.SamplewHeader;

public interface IRenvoieDonnesTraitees {
	List<SamplewHeader> renvoyerDonneesTraitees(List<String> lignesdefichier);
}