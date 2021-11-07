#!/bin/bash -xe

# First, checking if bucket exists
BUCKET_NAME=aws-java-example-artefacts
echo "Checking if bucket ${BUCKET_NAME} exists"

BUCKET_COUNT=$(aws s3 ls | grep ${BUCKET_NAME} | wc -l)

if [ $BUCKET_COUNT == "0" ]; then
  echo "Bucket ${BUCKET_NAME} does not exist"
  echo "Create the bucket first"
  exit 1
fi

# Uploading files
aws s3 cp ../../challenges-provider/build/libs/challenges-provider.jar \
  s3://${BUCKET_NAME}/latest/challenges-provider.jar

aws s3 cp ../../services/discovery-server/build/libs/discovery-server.jar \
  s3://${BUCKET_NAME}/latest/discovery-server.jar

aws s3 cp ../../providers/provider-history/build/libs/provider-history.jar \
  s3://${BUCKET_NAME}/latest/provider-history.jar

aws s3 cp ../../providers/provider-math/build/libs/provider-math.jar \
  s3://${BUCKET_NAME}/latest/provider-math.jar

aws s3 cp ./app-run.sh s3://${BUCKET_NAME}/latest/app-run.sh