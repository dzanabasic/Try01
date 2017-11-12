# README #

This repository contains code for a 16S analysis pipeline for a single fecal sample. It includes quality filtering, OTU picking, some taxonomic analysis, and comparisons to publicly available anonymous data from American Gut (AG). It generates (among other output) pie / bar charts of taxonomy and PCoA figures comparing the sample under study to AG samples.

### setup.sh

More a guide than an executable shell script. The setup has already been done on the bioinformatics azure instance.

### single_fecal.cwl

The pipeline for a single sample. Takes 2 .fastq.gz files and produces plots.

From a directory with 2 .fastq.gz files: `cwl-runner ~/veritas-16s/single_fecal.cwl --dir $PWD`

### all_fecal.sh

Makes one dir for each .fastq.gz pair, then calls single_fecal.cwl in each dir sequentially. Use this to quickly analyse all samples from an illumina run.
So from a directory like:

	veritaslab@VeritasLab16CoreUbuntu:/datadrive/aa$ ls -larth
	total 196K
	-rw-rw-r-- 1 veritaslab veritaslab  51K Nov  8 22:05 24-PC-ARD160919_S24_L001_R2_001.fastq.gz
	-rw-rw-r-- 1 veritaslab veritaslab  41K Nov  8 22:05 24-PC-ARD160919_S24_L001_R1_001.fastq.gz
	-rw-rw-r-- 1 veritaslab veritaslab  49K Nov  8 22:05 23-HM-783D_S23_L001_R2_001.fastq.gz
	-rw-rw-r-- 1 veritaslab veritaslab  37K Nov  8 22:05 23-HM-783D_S23_L001_R1_001.fastq.gz
	drwxrwxrwx 9 root       root       4.0K Nov  8 22:05 ..
	drwxrwxr-x 2 veritaslab veritaslab 4.0K Nov  8 22:05 .

just do `all_fecal.sh`

### make_map.py

Helper script used by the pipeline. This should be accessible in your $PATH (and executable (chmod +x)).
