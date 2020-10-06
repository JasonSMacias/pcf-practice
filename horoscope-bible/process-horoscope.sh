#!/bin/bash

#############################################
# Author: Jason Macias
# Version: v1.0.0
# Date: 10/6/20
# Description: Take input json and get words from description at least 5 characters long, pass on in task output folder
# Usage: concourse with horoscope in json format
#############################################

echo "start process horoscope"
# for safe scripts
set -euf -o pipefail

if [[ ! -d horoscope-words ]]; then
    echo "making output directory"
    mkdir horoscope-words || echo "directory make fail"; exit 1
fi

echo "file passed from previous task (horoscope)"
ls -l horoscope-json
echo "contains:"
cd horoscope-json
cat $(ls) | jq .
echo "description"
DESCRIPTION=$(cat $(ls) | jq .description)
END_MIN_2=$(( #DESCRIPTION - 2 ))
DESCRIPTION=${DESCRIPTION:1:END_MIN_2}
desc_words="${DESCRIPTION//.}"
echo $desc_words
desc_arr=($desc_words)
echo "word array items"
longer_than_5_arr=()
printf "\nLonger Words:"
for i in "${desc_arr[@]}"
do  
    if [[ ${#i} -ge 5 ]] ; then
        echo $i
        longer_than_5_arr+=($i)
    fi
done
printf "\nLonger Words Array:"
echo ${longer_than_5_arr[@]}
cd ../horoscope-words
echo "(${longer_than_5_arr[@]})" > longer_than_5.txt
echo "file to output:"
cat longer_than_5.txt