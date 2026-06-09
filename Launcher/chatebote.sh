#!/bin/bash

xfce4-terminal -x bash \
    -c "export GROQ_API_KEY='$GROQ_API_KEY'; java -jar ../app/build/libs/Chatebote.jar '$1'"