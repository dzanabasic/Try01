#!/bin/bash
# input: overlapping forward and reverse reads in  2 .fastq.gz files
INIT_PWD=$PWD

cd $1
source activate qiime1
mkdir -p .config/matplotlib
echo "backend : agg" > .config/matplotlib/matplotlibrc
export XDG_CONFIG_HOME="$PWD/.config"

NUM_CPUS=`grep -c ^processor /proc/cpuinfo` || NUM_CPUS=2
TAX_PATH=`python -c 'import qiime.util; print(qiime.util.get_reference_taxonomy());'`
REF_SEQS_PATH=`python -c 'import qiime.util; print(qiime.util.get_reference_sequences());'`
TREE_PATH=`python -c 'import qiime.util; print(qiime.util.get_reference_taxonomy());' | sed s@taxonomy/97_otu_taxonomy.txt@trees/97_otus.tree@`

# make sure AGDIR is set: see 'export AGDIR=$PWD' from setup.sh

cd joined
cd sl
cd po_closed

summarize_taxa.py -i otu_table.biom -o sumtax -L 2,3,4,5,6,7
plot_taxa_summary.py -i sumtax/otu_table_L2.txt,sumtax/otu_table_L3.txt,sumtax/otu_table_L4.txt,sumtax/otu_table_L5.txt,sumtax/otu_table_L6.txt,sumtax/otu_table_L7.txt -o sumtax/taxa_summary_plots/ -c bar,pie


## merge with AG
single_rarefaction.py -i otu_table.biom -o ver_10k.biom -d 10000 # match with AG data
make_map.py $AGDIR/ag_10k_fecal.txt > map101.txt # note make_map.py is a custom script
filter_samples_from_otu_table.py -i $AGDIR/ag_10k_fecal.biom -o 100.biom -m map101.txt -s 'source:*'
merge_otu_tables.py -i ver_10k.biom,100.biom -o 101.biom

# to manually check the status:
# biom summarize-table -i 101.biom

beta_diversity_through_plots.py -i 101.biom -m map101.txt -o bd101 -t $TREE_PATH
summarize_taxa.py -i 101.biom -o sumtax101 -L 2,3,4,5,6,7
plot_taxa_summary.py -i sumtax101/101_L2.txt,sumtax101/101_L3.txt,sumtax101/101_L4.txt,sumtax101/101_L5.txt,sumtax101/101_L6.txt,sumtax101/101_L7.txt -o sumtax101/taxa_summary_plots/ -c bar,pie

cd $INIT_PWD
touch "single_otu_to_figs.done"
