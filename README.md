**AdvancedQC**
The goal of this project is to realise  a quality control on NGS data. It allows to generate json files and a multiQC report.The report will help to detect inconsistency and determine if these NGS data can be use for a study

--------
*Requirements:*
- Java 13
- [MultiQC](https://github.com/ewels/MultiQC)

------
*Usage*

`Java -jar <absolute_path>/advancedQC-1.0.0.jar <absolute_path>/fichier.tsv <local directory>`

`multiqc <local directory> -c <local directory>/advancedQC/MultiQC_file/multiqc_config.yaml`

-------
*Details*
- `<absolute_path>/fichier.tsv` is the path to the file which will be analysed.
- the tsv file should look like this:  

 `target_id	28_UT7_parentale_SRSF2_WT_S28/	29_UT7_parentale_SRSF2_WT_S29/`
 
 `ENST00000387460.2	0.0	0.0`
 
 `ENST00000387459.1	906.4	786.8710000000001`

- `<local directory>` is the directory where the json files will be created.
In order to create a MultiQc report you have to scan the directory where the json files are saved.
- You must also add the customized config file named "multiqc_config.yaml". This command is necessary to obtain the beeswarm.
- The emplacement of the report can be chose by adding this command:`-o <local directory>/report/`

-------
*How to build a jar*
- Download [Maven](https://maven.apache.org/download.cgi)
- Clone the project
- From advancedQC's directory, run "mvn install"
