package fr.gustaveroussy.AdvancedQC;

import java.util.Map;
import java.util.Map.Entry;

public class Compteur {
	

	public static int compteur (Map <String,Double> MaMap) {
		int compteur = 0;
		for (Entry <String, Double> entry: MaMap.entrySet()) {
			if(entry.getValue().equals(0.0)) {
				compteur  = compteur+1 ;
			}
		}
		return compteur;
		
	}
		
	}


	

 

