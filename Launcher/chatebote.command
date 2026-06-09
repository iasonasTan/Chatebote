#!/bin/bash

# THIS SCRIPT IS NOT TESTED
# A MAC-OS USER MAY TEST IT AND REMOVE THIS COMMENT

cd "$(dirname "$0")"
open -a Terminal.app --args java -jar ../app/build/libs/Chatebote.jar "$1"