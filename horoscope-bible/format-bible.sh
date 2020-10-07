#!/bin/bash

#############################################
# Author: Jason Macias
# Version: v1.0.0
# Date: 10/6/20
# Description: Takes an array of words, gets bible, prints head of bible, Java hello world
# Usage: concourse with words from horoscope in text file string to be made an array
#############################################

echo "start format bible"
# for safe scripts
set -euf -o pipefail

echo "words in array from input:"
HOROSCOPE_WORDS_ARRAY=$(cat horoscope-words/longer_than_5.txt)
for word in ${HOROSCOPE_WORDS_ARRAY[@]}
do
    echo $word
done

echo "version of Java running:"
java --version

cd horoscope-repo/horoscope-bible/java
javac GetBible.java
java GetBible || echo "Bible not written"; exit 1
head bible.txt
BIB_LINE_COUNT=$( wc -l bible.txt)

printf "---------\nBible Verses with words from daily Horoscope description"
for word in ${HOROSCOPE_WORDS_ARRAY[@]}
do
  java GetBible --get-verses $BIB_LINE_COUNT word
done