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

wget http://www.gutenberg.org/cache/epub/10/pg10.txt
echo "after getting text"
ls -l

echo "File:"

echo "version of Java running:"
java --version

cd horoscope-repo/horoscope-bible/java
javac SayHello
java SayHello