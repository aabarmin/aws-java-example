#!/bin/bash

function unpack() {
  FOLDER=$1
  NAME=$2

  CURRENT=$(pwd)

  cd $FOLDER/build/libs
  java -jar -Djarmode=layertools ${NAME} extract

  cd $CURRENT
}

function build() {
  FOLDER=$1
  NAME=$2

  docker build -f ./aws/03_ecs/docker/layered/Dockerfile \
    --build-arg JAR_FOLDER=${FOLDER}/build/libs \
    -t ${NAME}:latest .
}

# Building the app
cd ../..

echo "Building JAR files"
./gradlew clean build -x test

echo "Unpacking JARs"
unpack challenges-provider challenges-provider.jar
unpack providers/provider-math provider-math.jar
unpack providers/provider-history provider-history.jar
unpack services/discovery-server discovery-server.jar

echo "Building Docker image"
build challenges-provider application/challenges-provider
build providers/provider-math application/provider-math
build providers/provider-history application/provider-history
build services/discovery-server application/service-discovery
