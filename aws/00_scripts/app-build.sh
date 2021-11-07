#!/bin/bash

# Building the app
CURRENT_DIR=$(pwd)
cd ../..

echo "Building the app"
GRADLE_OPTS=-Dorg.gradle.daemon=false
./gradlew clean build

echo "Done"
cd $CURRENT_DIR