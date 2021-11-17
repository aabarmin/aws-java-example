#!/bin/bash

# Building the app
cd ../..

echo "Building JAR files"
./gradlew clean build -x test

echo "Building Docker images"

echo "Building Service Discovery image"
docker build -f ./aws/03_ecs/docker/basic/Dockerfile \
  --build-arg JAR_FILE=./services/discovery-server/build/libs/discovery-server.jar \
  -t application/service-discovery:latest \
  -t application/service-discovery:naive .

echo "Building Challenges Provider image"
docker build -f ./aws/03_ecs/docker/basic/Dockerfile \
  --build-arg JAR_FILE=./challenges-provider/build/libs/challenges-provider.jar \
  -t application/challenges-provider:latest \
  -t application/challenges-provider:naive .

echo "Building Provider Math image"
docker build -f ./aws/03_ecs/docker/basic/Dockerfile \
  --build-arg JAR_FILE=./providers/provider-math/build/libs/provider-math.jar \
  -t application/provider-math:latest \
  -t application/provider-math:naive .

echo "Building Provider History image"
docker build -f ./aws/03_ecs/docker/basic/Dockerfile \
  --build-arg JAR_FILE=./providers/provider-history/build/libs/provider-history.jar \
  -t application/provider-history:latest \
  -t application/provider-history:naive .