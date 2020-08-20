#!/bin/bash
cd ./src/mtg.mendonca/
b=0
i=0
ls |
while read a ; do
    i=$(($i+1))
    b=$(( $b + $(cat $a | wc -l)))
if [ $i == $(ls | wc -l) ] ; then
    echo "The total number of lines of the whole program is" $b "lines."
fi
done
cat $(ls) | wc -l
sleep 5
