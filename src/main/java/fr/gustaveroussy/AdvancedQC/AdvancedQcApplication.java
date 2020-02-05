package fr.gustaveroussy.AdvancedQC;



import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files ;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;






@SpringBootApplication
public class AdvancedQcApplication implements CommandLineRunner {
	
	private static Logger LOG = LoggerFactory
		      .getLogger(AdvancedQcApplication.class);
	
	public static void main(String[] args) {
		LOG.info("Hello World ");
		SpringApplication.run(AdvancedQcApplication.class, args);
		LOG.info("Bye Bye World");
		
	}

	
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
			if(args.length == 1) {
			List<String> lines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);  
			
			List<Map<String, Double>> toutesMesMaps =renvoieDonneesTraitees(lines);//données exploitables création de la premiere map
			remplirMapPourcent(toutesMesMaps);
			PourcentageTotal monpourcent = new PourcentageTotal();
			monpourcent.pourcenTotale(toutesMesMaps);
			
			
			
		}else {
			LOG.error("args must be 1");
		}
	}
	
	
// méthode RENVOIE DONNEES TRAITEES,modifiée pour obtenir un set de données qui sera réutiliser par les autre parties du prgrm
public List<Map<String,Double>> renvoieDonneesTraitees (List<String> lignesdefichier){
	List<Map<String, Double>> toutesMesMaps = new ArrayList<Map<String, Double>>();

Map<String,Double> maMap = null;
lignesdefichier.remove(0); //elimination du header de la liste "lines"
	 String[] toutesmescolonnes = lignesdefichier.get(0).split("\t");	
		for (int i = 1; i<toutesmescolonnes.length; i++) {
			maMap=transformListenTabExploitable(lignesdefichier, i);	
		    toutesMesMaps.add(maMap);		    
		}
	  LOG.info ( "Mes Maps: " + toutesMesMaps);//renvoie les maps avec les valeurs d'origine et non les valeurs calculées
	  LOG.info ( "taille de mesMaps: " + toutesMesMaps.size());
		return (toutesMesMaps);
}

	
//Méthode pour créer, à partir du fichier .tsv, un tableau de données exploitable
public Map<String, Double> transformListenTabExploitable(List<String> lignesatransformer, Integer numechantillon) {
	Map<String,Double> maMap = new HashMap<String,Double>();
		for(String str : lignesatransformer){	
			 String[] toutesmescolonnes = str.split("\t");			
				String valeurStringdufichier = toutesmescolonnes [numechantillon]; 
				double valeurDoubledufichier = Double.parseDouble (valeurStringdufichier);	
				maMap.put(toutesmescolonnes [0], valeurDoubledufichier);
		}
		LOG.info( "map exploitable"+maMap);
		return (maMap) ;
		}
	
//Modif méthode pour CREATION DU NVL MAP À PARTIR DE MAP DEJA EXISTANTE, la meth doit prendre en entrée une liste de maps et renvoyer une map ac % taux expression		 
	public Map<String, Double> remplirMapPourcent(List<Map<String, Double>> toutesMesMaps) {
		LOG.info("mon set de map"+ toutesMesMaps);
		Map<String, Double> mapMeanByGene = new HashMap<String,Double>();
		
		for (String geneKey : toutesMesMaps.get(0).keySet() ) {
        	DescriptiveStatistics stats = new DescriptiveStatistics ();
        	Iterator<Map<String, Double>> it = toutesMesMaps.iterator();
    		
			//itération dans la liste
	        while(it.hasNext()) { 		
	        	stats.addValue(it.next().get(geneKey) );
	        	
	        }
	        
	        mapMeanByGene.put(geneKey, stats.getMean());    
		}
		 LOG.info("remplirMapPourcent : "+mapMeanByGene.toString());
		
		  return mapMeanByGene;		  
        }	
}
	

