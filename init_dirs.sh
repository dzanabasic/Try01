#!/bin/bash
for fname in `ls | grep 'L001_R1' `; do
  samplename=$( echo "$fname" | cut -d _ -f 1 );
  mkdir "$samplename"
  mv "$fname" "$samplename"
  mv `echo $fname | sed s/L001_R1/L001_R2/` "$samplename"
done
