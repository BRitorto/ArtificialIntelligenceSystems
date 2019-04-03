#!/bin/bash

setup(){
    cd skyscrapers-puzzle/
    mvn clean compile assembly:single
    cd target
    clear
}

RED='\033[0;31m'
NC='\033[0m' # No Color

if [ $# -eq 0 ]; then
    setup
    java -jar skyscrapers-puzzle-1.0-SNAPSHOT-jar-with-dependencies.jar
else
    if [ $# -ne 1 ]; then
        echo -e "${RED}Error${NC}: Correct Usage: ./execute or ./execute [example_id]"
    else
        setup
        java -jar skyscrapers-puzzle-1.0-SNAPSHOT-jar-with-dependencies.jar $1
    fi
fi


