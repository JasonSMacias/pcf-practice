#!/bin/bash

#############################################
# Author: Jason Macias
# Version: v1.0.0
# Date: 10/6/20
# Description: 
# Usage: concourse with horoscope in json format
#############################################

# for safe scripts
set -euf -o pipefail

if [[ ! -d horoscope-files ]]; then
    mkdir horoscope-files || echo "directory make fail"
fi

echo "file passed from previous task (horoscope), and it's contents"
ls -l horoscope
echo "contains"
cat $(ls horoscope) | jq .