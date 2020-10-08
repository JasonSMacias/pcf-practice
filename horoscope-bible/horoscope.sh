#!/bin/bash

# A bash script for Horoscope/Bible concourse pipeline
if [[ ! -d horoscope-json ]]; then
    mkdir horoscope-json
fi

echo "environment variables:"
env

SIGNS=(aries taurus gemini cancer leo virgo libra scorpio sagittarius capricorn aquarius pisces)
sign_arg=$1
for sign in "${SIGNS[@]}"
do
    if [[ $sign = $SIGN_NAME ]]; then
        sign_arg=$sign
    fi
done

curl -X POST \
"https://aztro.sameerkumar.website/?sign=$sign_arg&day=today"  > horoscope-json/result$(date +%d-%m-%y).json
ls -l


ls -l horoscope-json
cat horoscope-json/result$(date +%d-%m-%y).json | jq .

echo "horoscope.sh completed"