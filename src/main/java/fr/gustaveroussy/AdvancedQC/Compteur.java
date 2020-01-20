package fr.gustaveroussy.AdvancedQC;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Compteur {
	private static final Map<String, Double> MaMap = new HashMap<>();
	private  double Counter



	public static Compteur (Map <String, Double>){
		for (Entry<String, Double> entry : MaMap.entrySet()) {
			 if (entry.getValue().equals(0.0)){ 	
		         			Counter = Counter + 1 ;
		         }  
			 }
			 System.out.println("il y a "+Counter+ " valeurs nulles dans cet echantillon.");
			return null;	
			
		}



	

 

