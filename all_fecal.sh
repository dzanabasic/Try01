#!/bin/bash
init_dirs.sh
for d in */; do
  echo "running single_fecal.cwl in $d"
  cd $d
  cwl-runner ~/veritas-16s/single_fecal.cwl --dir $PWD
  cd ..
done
