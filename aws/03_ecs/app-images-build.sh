#!/bin/bash

source ./functions.sh

# Building the app
CURRENT_DIR=$(pwd)

cd ../..

echo "Building JAR files"
#./gradlew clean build -x test

echo "Building Docker images"


echo "Building Service Discovery image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./services/discovery-server/build/libs/discovery-server.jar \
  -t application/service-discovery \
  -t $(ecr_repository_tag "app-discovery-server"):latest \
  .

echo "Building Challenges Provider image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./challenges-provider/build/libs/challenges-provider.jar \
  -t application/challenges-provider \
  -t $(ecr_repository_tag "app-challenges-provider"):latest \
  .

echo "Building Provider Math image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./providers/provider-math/build/libs/provider-math.jar \
  -t application/provider-math \
  -t $(ecr_repository_tag "app-provider-math"):latest \
  .

echo "Building Provider History image"
docker build -f ./aws/03_ecs/Dockerfile \
  --build-arg JAR_FILE=./providers/provider-history/build/libs/provider-history.jar \
  -t application/provider-history \
  -t $(ecr_repository_tag "app-provider-history"):latest \
  .

echo "Authenticating in ECR"
ECR_URL=$(ecr_repository_tag "app-challenges-provider")
ECR_NAME=$(awk '{print substr($0, 1, index($0, "/") - 1)}' <<< $ECR_URL)
aws ecr get-login-password | docker login --username AWS --password-stdin $ECR_NAME

echo "Pushing images to ECR"
docker push $(ecr_repository_tag "app-discovery-server"):latest
docker push $(ecr_repository_tag "app-challenges-provider"):latest
docker push $(ecr_repository_tag "app-provider-math"):latest
docker push $(ecr_repository_tag "app-provider-history"):latest