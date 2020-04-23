**AdvancedQC**
Le but de ce projet est d'effectuer un contrôle qualité sur des données NGS.
============================================================================
**Pré-requis:**
-Eclipse
-Java 13
-Springboot
-MultiQC
-fichier de données au format tsv

-------------------------------------------------------------------------------
**INSTRUCTION**
 1.Créer un jar
 2.Exécuter le jar en ajoutant 2 arguments:
 - Le premier argument est le chemin vers le fichier données à analyser et entrer le chemin absolu vers ce fichier.
 - Le second argument correspond au répertoire dans lequel les fichiers json seront créés.
*exemple:*
Java -jar /Users/hadidjasaid/Documents/GitHub/advancedQC/target/advancedQC-1.0.0.jar /Users/hadidjasaid/Documents/complete_example_dataset.tsv /Users/hadidjasaid/Documents/

3.Créer un rapport avec multiQC avec en argument le répertoire utilisé pour stocker les fichier json ainsi que le fichier multiqc_config.yaml
*exemple:*
MultiQC /Users/hadidjasaid/Documents -c /Users/hadidjasaid/Documents/GitHub/advancedQC/MultiQC_file/multiqc_config.yaml
 - Il est possible de choisir l'emplacement du rapport. Par exemple pour que le rapport soit généré dans le dossier Documents, il suffit d'ajouter à la suite de la commande:  -o /Users/hadidjasaid/Documents
 


> Written with [StackEdit](https://stackedit.io/).