#!/bin/bash

#############################################
# Author: Jason Macias
# Version: v1.0.0
# Date: 10/6/20
# Description: 
# Usage: concourse with horoscope in json format
#############################################
echo "start process horoscope"
# for safe scripts
set -euf -o pipefail

if [[ ! -d horoscope-files ]]; then
    mkdir horoscope-files || echo "directory make fail"
fi

echo "file passed from previous task (horoscope)"
ls -l horoscope-json
echo "contains:"
cd horoscope-json
cat $(ls) | jq .
echo "description"
DESCRIPTION=$(cat $(ls) | jq .description)
desc_words="${DESCRIPTION//.}"
echo $desc_words
desc_arr=($desc_words)
echo "word array items"
for i in "${desc_arr[@]}"
do
    echo $i
done