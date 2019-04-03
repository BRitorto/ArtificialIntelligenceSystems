#!/bin/bash

cd skyscrapers-puzzle/
mvn clean compile assembly:single
cd target
clear
echo WELCOME TO SKYSCRAPER PUZZLE SOLVER
java -jar skyscrapers-puzzle-1.0-SNAPSHOT-jar-with-dependencies.jar
