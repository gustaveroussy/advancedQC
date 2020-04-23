#!/bin/sh
Multiqc /users/hadidjasaid/data/test_creation_json -o /users/hadidjasaid/data/esp_test -n report_test_mqc -x fastqc_data.txt -c /users/hadidjasaid/data/test_creation_json/multiqc_config.yaml -i customizedTitle -b myComment
#  test_multiQC2.sh
#lancement depuis MultiQC, scan du dossier 'test_creation_json'
#-o: rapport créé dans le dossier 'esp_test'
#-n: om du dossier contenant les fichiers générés par multiQC est 'report_test'
#-x: ici on ne souhaite pas que multitQc tienne compte du fichier 'fastqc_data.txt'
#-c: le fichier 'multiqc_config.yaml' sera utilié à la place du fichier de configuration par defaut.
#
#  Created by Hadidja Said on 12/03/2020.
#  
