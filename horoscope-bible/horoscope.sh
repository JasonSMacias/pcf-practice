#!/bin/bash
# A bash script for Horoscope/Bible concourse pipeline
# To use: Alpine

# below to get jq, commented out for now
# RUN apk add --update --no-cache curl jq

curl -X POST \
"https://aztro.sameerkumar.website/?sign=$1&day=today"  > result$(date +%d-%m-%y).json
ls -l
cat result$(date +%d-%m-%y).json