#!/bin/bash

# A bash script for Horoscope/Bible concourse pipeline
if [[ ! -d horoscope-json ]]; then
    mkdir horoscope-json
fi

curl -X POST \
"https://aztro.sameerkumar.website/?sign=$1&day=today"  > horoscope-json/result$(date +%d-%m-%y).json
ls -l


ls -l horoscope-json
cat horoscope-json/result$(date +%d-%m-%y).json | jq .

echo "horoscope.sh completed"