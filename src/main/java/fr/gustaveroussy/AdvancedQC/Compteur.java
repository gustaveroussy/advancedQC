package fr.gustaveroussy.AdvancedQC;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Compteur {
	

	public int Compteur (List<Map<String, Double>> toutesMesMaps) {
		 int Counter = 0;
		 for (int i= 0 ; i<toutesMesMaps.size (); i++){
			for (Double geneVal : toutesMesMaps.get(i).values()) {
				if( geneVal.equals (0.0)){
					Counter = Counter+1; 	
					}
			}
		}
		 
		 System.out.println ("Il y a "+ Counter+ " valeurs nulles ");
	return Counter;
		 
	} 
}


 

