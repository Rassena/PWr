#!/bin/bash

#Z1
mkdir -p $1

#Z2
cd $1

#Z3
mkdir -p ./d1/d1.1 d2/d2.1 d3/d3.1 d3/d3.2 Aa Bb Cc
touch d1/t1.txt
touch d2/d2.1/f1.csv
touch d3/d3.2/f2.csv

#Z4
mv d1/t1.txt d1/d1.1/.

# #Z5
cp d2/d2.1/f1.csv d1/. 

#Z6
mv Aa Aa1

#Z7
ls *1 *2

#Z8
du -d 1 .
