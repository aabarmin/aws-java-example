#!/bin/bash

BUCKET_NAME=$1
if [ -z $BUCKET_NAME ]; then
  echo "Bucket name was not provided"
  echo "Usage: ./bucket-delete.sh bucket"
  exit 1
fi

BUCKET_COUNT=$(aws s3 ls | grep ${BUCKET_NAME} | wc -l)

if [ $BUCKET_COUNT == "0" ]; then
  echo "Bucket ${BUCKET_NAME} does not exist"
  echo "Create the bucket first"
  exit 1
fi

aws s3 rm s3://${BUCKET_NAME} --recursive