# put the included make_map.py in your $PATH
# setup cwl stuff
pip install cwlref-runner

## american gut (ag) setup (put the files somewhere accessible
wget ftp://ftp.microbio.me:21/AmericanGut/latest/11-packaged/fecal/100nt/all_participants/all_samples/10k/ag_10k_fecal.biom
wget ftp://ftp.microbio.me:21/AmericanGut/latest/11-packaged/fecal/100nt/all_participants/all_samples/10k/ag_10k_fecal.txt
export AGDIR=$PWD

## setup w conda
wget https://repo.continuum.io/miniconda/Miniconda2-latest-Linux-x86_64.sh
bash Miniconda2-latest-Linux-x86_64.sh
#yes to all
conda create -n qiime1 python=2.7 qiime matplotlib=1.4.3 mock nose -c bioconda
source activate qiime1

sudo apt-get install -y python-qt4 

conda config --add channels r
conda config --add channels bioconda
conda install fastq-join

## troubles with matplotlib?
# create (or modify) the matplotlib config file :
# ~/.config/matplotlib/matplotlibrc
# and add the following:
# backend : agg

## to copy sequence files from basespace:
# install basespace cli tools, basemount basespace
# veritaslab@VeritasLab16CoreUbuntu:/datadrive/basespace/Runs/ARD_160802/Samples$ find . -name '*.fastq.gz' -exec cp "{}" /datadrive/ARD_160802_fastqs/ \;

