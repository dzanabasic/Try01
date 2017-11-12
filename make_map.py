#! /usr/bin/env python2

import sys
with open(sys.argv[1], 'U') as fh:
    for i, line in enumerate(fh):
        if i == 101: break
        if i == 0:
            print "#SampleID\tsource"
        else:
            print line.split('\t')[0] + "\tAG"

    print "yours\tYou"
