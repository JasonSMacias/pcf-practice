#!/bin/bash

# A bash script for Horoscope/Bible concourse pipeline

curl -X POST \
"https://aztro.sameerkumar.website/?sign=$1&day=today"  > result$(date +%d-%m-%y).json
ls -l

cat result$(date +%d-%m-%y).json | jq .
mkdir horoscope-json && mv result$(date +%d-%m-%y).json ./horoscope-json
ls -l horoscope-json