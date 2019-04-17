#!/usr/bin/env bash

# This packages the project up as a jar with all the dependencies and runs the jar

mvn package
java -jar target/mpspark-1.0-SNAPSHOT-jar-with-dependencies.jar