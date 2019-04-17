#!/usr/bin/env bash

# This runs the project simply using the java executable

mvn dependency:copy-dependencies
mvn compile
java -cp "target/classes;target/dependency/*" com.bcreagh.mpspark.App