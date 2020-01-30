package fr.gustaveroussy.AdvancedQC;

import java.util.Map;
import java.util.Map.Entry;

public class Compteur {
	

	public static int Compteur (Map <String,Double> MaMap) {
		int counter = 0;
		for (Entry <String, Double> entry: MaMap.entrySet()) {
			if(entry.getValue().equals(0.0)) {
				counter  = counter+1 ;
			}
		}
		System.out.println ("Il y a "+ counter+ " valeurs nulles");
		return counter;
		
	}
		
	}


 

