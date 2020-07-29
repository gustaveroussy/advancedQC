#!/bin/bash

loc_data=data/New_complete_example_dataset.tsv
loc_design=data/complete_example_design.tsv

rm output/*.json
rm output/*.html
rm -R report/*

module load java/1.8.0_181
java -jar advancedQC-release/advancedQC-1.0.2.jar $loc_data output/ $loc_design

#conda activate multiqc1.9
multiqc output -o report -n reportAdvancedQC -i "Custom advancedQC report" -b "powered by https://github.com/gustaveroussy/advancedQC"
