#!/bin/bash

source ./functions.sh

# Building the app
CURRENT_DIR=$(pwd)

cd ../..

echo "Building JAR files"
./gradlew clean build -x test

echo "Building Docker images"

echo "Building Service Discovery image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./services/discovery-server/build/libs/discovery-server.jar \
  -t application/service-discovery:latest \
  .

echo "Building Challenges Provider image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./challenges-provider/build/libs/challenges-provider.jar \
  -t application/challenges-provider:latest \
  .

echo "Building Provider Math image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./providers/provider-math/build/libs/provider-math.jar \
  -t application/provider-math:latest \
  .

echo "Building Provider History image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./providers/provider-history/build/libs/provider-history.jar \
  -t application/provider-history:latest \
  .