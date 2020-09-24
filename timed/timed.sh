#!/usr/bin/env bash

#############################################
# Author: Jason Macias
# Version: v1.0.0
# Date: 9/24/2020
# Description: prints sentence with current date/time
# Usage: no arguments
#############################################

echo "Hello, I ran on $(date +%A) $(date +%B) $(date +%d), $(date +%y), at $(TZ=America/New_York date +%r)."