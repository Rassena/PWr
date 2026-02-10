#!/bin/bash

#Z1
mkdir -p $2

#Z2
cp -r $1 $2/.

#Z3
cd $2

#Z4
ls -l

#Z5
tree --du -h .

#Z6
pwd

#Z7
cd -

#Z8
rm -rf $2

