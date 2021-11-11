#!/bin/bash

source ./functions.sh

function docker_image_exists() {
  local REPOSITORY=$1
  local NUMBER=$(docker image ls ${REPOSITORY} | wc -l)

  if [ ${NUMBER} != "2" ]; then
    echo "Image ${REPOSITORY} does not exist"
    exit 1
  fi
}

function docker_image_mark() {
  local OLD=$1
  local NEW=$2

  docker image tag ${OLD} ${NEW}
}

# Make sure docker images are built for all the services
echo "Checking images were built previously"
docker_image_exists "application/challenges-provider:latest"
docker_image_exists "application/provider-math:latest"
docker_image_exists "application/provider-history:latest"
echo "All the images are available"

# Marking images with ECR repo tags
echo "Marking images with ECR tags"
docker_image_mark "application/challenges-provider:latest" "$(ecr_repository_tag "app-challenges-provider"):latest"
docker_image_mark "application/provider-math:latest" "$(ecr_repository_tag "app-provider-math"):latest"
docker_image_mark "application/provider-history:latest" "$(ecr_repository_tag "app-provider-history"):latest"
echo "Done"

# echo "Authenticating in ECR"
echo "Logging into ECR"
ECR_URL=$(ecr_repository_tag "app-challenges-provider")
ECR_NAME=$(awk '{print substr($0, 1, index($0, "/") - 1)}' <<< $ECR_URL)
aws ecr get-login-password | docker login --username AWS --password-stdin $ECR_NAME
echo "Done"

# echo "Pushing images to ECR"
echo "Pushing images to ECR"
docker push $(ecr_repository_tag "app-challenges-provider"):latest
docker push $(ecr_repository_tag "app-provider-math"):latest
docker push $(ecr_repository_tag "app-provider-history"):latest
echo "Done"