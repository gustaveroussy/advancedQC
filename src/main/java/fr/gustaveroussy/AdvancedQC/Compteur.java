package fr.gustaveroussy.AdvancedQC;

import java.util.Map;
import java.util.Map.Entry;

public class Compteur {
	

	public static int Counter (Map <String,Double> MaMap) {
		int count = 0;
		for (Entry <String, Double> entry: MaMap.entrySet()) {
			if(entry.getValue().equals(0.0)) {
				count  = count+1 ;
			}
		}
		return count;
		
	}
		
	}


	

 

