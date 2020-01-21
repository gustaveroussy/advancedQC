package fr.gustaveroussy.AdvancedQC;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Compteur {
	public HashMap<String, Double> MaMap = new HashMap<>();
	public static int Counter;

	private void Compteur (Map <String,Double> MaMap) {
		for (Entry <String, Double> entry: MaMap.entrySet()) {
			if(entry.getValue().equals(0.0)) {
				Counter = Counter+1 ;
			}
		}
		System.out.println ("Il y a "+Counter+ " valeurs nulles");
	}
		
	}


	

 

