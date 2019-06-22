#!/bin/bash

setup(){
    cd system/
    mvn clean compile assembly:single
    cd ..
    clear
}

GREEN='\e[32m'
NC='\033[0m' # No color

setup

echo -e "${GREEN}EXECUTING GENETIC ALGORITHM ${NC}"
echo "Please stand by... we need to load a lot of data!"
java -jar system/target/engine-1.0-SNAPSHOT-jar-with-dependencies.jar

echo -e "${GREEN}EXECUTION FINISHED SUCCESSFULLY"
