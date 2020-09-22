#!/bin/bash
# A bash script for Horoscope/Bible concourse pipeline

# sudo apt update
# sudo apt-get install jq

curl -X POST \
"https://aztro.sameerkumar.website/?sign=$1&day=today"  > result$(date +%d-%m-%y).json
ls -l

cat result$(date +%d-%m-%y).json | jq .