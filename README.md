**AdvancedQC**
The goal of this project is to realise  a quality control on NGS data. It allows to generate json files and a multiQC report.The report will help to detect inconsistency and determine if these NGS data can be use for a study

--------
*Requirements:*
- Maven 3.6.3
- Java 13
- Springboot
- MultiQC

------
*Usage*
Java -jar <absolute_path>/fichier.jar <absolute_path>/fichier.tsv </local directory>

-------
*Details*
- `<absolute_path>/fichier.tsv ` is the path to the files which will be analysed.
- `</local directory >` is the directory where the json's files will be create.
In order to create a MultiQc report you have to scan the directory where the json files are saved.
example: `MultiQC  <local directory> -c <local directory>/GitHub/advancedQC/MultiQC_file/multiqc_config.yaml`.
- You must add the customized config file named "multiqc_config.yaml". This command is necessary to obtain the beeswarm.
- The emplacement of the report can be chose by adding this command:`-o <local directory>/Documents`
