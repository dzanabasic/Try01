#!/bin/bash
# input: overlapping forward and reverse reads in  2 .fastq.gz files
INIT_PWD=$PWD

cd $1
source activate qiime1

NUM_CPUS=`grep -c ^processor /proc/cpuinfo` || NUM_CPUS=2
TAX_PATH=`python -c 'import qiime.util; print(qiime.util.get_reference_taxonomy());'`
REF_SEQS_PATH=`python -c 'import qiime.util; print(qiime.util.get_reference_sequences());'`
TREE_PATH=`python -c 'import qiime.util; print(qiime.util.get_reference_taxonomy());' | sed s@taxonomy/97_otu_taxonomy.txt@trees/97_otus.tree@`

gunzip *
join_paired_ends.py -f `find . -name '*R1_001.fastq'` -r `find . -name '*R2_001.fastq'` -o joined
cd joined
split_libraries_fastq.py -i fastqjoin.join.fastq --sample_ids 'yours' -o sl --barcode_type not-barcoded
cd sl

# trivially parallelized, but still a slow step
parallel_pick_otus_uclust_ref.py -i seqs.fna -o po_closed/uclust_ref_picked_otus -r $REF_SEQS_PATH -Z 5 -T -O $NUM_CPUS

make_otu_table.py -i po_closed/uclust_ref_picked_otus/seqs_otus.txt -t $TAX_PATH -o po_closed/otu_table.biom

## to manually check the status:
# biom summarize-table -i otu_table.biom

cd $INIT_PWD
touch "single_gz_to_otu.done"