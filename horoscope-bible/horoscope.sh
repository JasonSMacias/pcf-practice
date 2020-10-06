#!/bin/bash

# A bash script for Horoscope/Bible concourse pipeline

curl -X POST \
"https://aztro.sameerkumar.website/?sign=$1&day=today"  > result$(date +%d-%m-%y).json
ls -l

cat result$(date +%d-%m-%y).json | jq .

if [[ ! -d horoscope-json ]]; then
    mkdir horoscope-json && mv result$(date +%d-%m-%y).json ./horoscope-json || echo "mkdir or move fail"
fi
ls -l horoscope-json
echo "horoscope.sh completed"